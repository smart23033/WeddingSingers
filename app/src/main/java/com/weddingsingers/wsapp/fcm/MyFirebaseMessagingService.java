/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weddingsingers.wsapp.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.SplashActivity;
import com.weddingsingers.wsapp.Utils;
import com.weddingsingers.wsapp.data.Alarm;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.ChatMessage;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.DBManager;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.AlarmListRequest;
import com.weddingsingers.wsapp.request.MessageListRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private static final String TYPE_RESERVE = "10";
    private static final String TYPE_REJECT = "11";
    private static final String TYPE_ACCEPT = "20";
    private static final String TYPE_CANCEL = "21";
    private static final String TYPE_PAY = "30";
    private static final String TYPE_CANCEL_SCHEDULE = "31";
    private static final String TYPE_FAVORITE = "50";
    private static final String TYPE_REVIEW = "60";
    private static final String TYPE_CHATTING = "70";

    public static final String ACTION_RESERVATION_LIST = "com.weddingsingers.wsapp.action.reservationmgm";
    public static final String ACTION_RESERVED_CUSTOMER = "com.weddingsingers.wsapp.action.reservedcustomer";
    public static final String ACTION_SCHEDULE_MGM = "com.weddingsingers.wsapp.action.schedulemgm";
    public static final String ACTION_VIDEO = "com.weddingsingers.wsapp.action.video";
    public static final String ACTION_SINGER_REVIEW = "com.weddingsingers.wsapp.action.singerreview";
    public static final String ACTION_CHATTING = "com.weddingsingers.wsapp.action.chatting";
    public static final String ACTION_CHAT_MESSAGE = "com.weddingsingers.wsapp.action.chatmessage";

    public static final String EXTRA_CHAT_USER = "chatuser";
    public static final String EXTRA_RESULT = "result";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]

    LocalBroadcastManager mLBM;

    @Override
    public void onCreate() {
        super.onCreate();
        mLBM = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.i(TAG, "From: " + remoteMessage.getFrom());
        Log.i(TAG, "remoteMessage.getData().size : " + remoteMessage.getData().size());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
            Log.i(TAG, "getData : " + remoteMessage.getData().get("type"));
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().get("type").equals(TYPE_CHATTING)) {
            chatSend();
        } else {
            //        서버로부터 내가 받지 못한 푸시메시지 요청
            AlarmListRequest alarmListRequest = new AlarmListRequest(this);
            NetworkManager.getInstance().getNetworkData(alarmListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Alarm>>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<List<Alarm>>> request, NetworkResult<List<Alarm>> result) {
                    List<Alarm> list = result.getResult();

                    for (Alarm alarm : list) {
//                    TYPE에 따라 다른 액티비티로 보내는 인텐트 생성

                        String type = String.valueOf(alarm.getType());

                        switch (type) {
                            case TYPE_RESERVE: {

                                makeAndSendIntent(alarm, ACTION_RESERVED_CUSTOMER);

                                break;
                            }
                            case TYPE_REJECT: {

                                makeAndSendIntent(alarm, ACTION_RESERVATION_LIST);

                                break;
                            }
                            case TYPE_ACCEPT: {

                                makeAndSendIntent(alarm, ACTION_RESERVATION_LIST);
                                break;
                            }
                            case TYPE_CANCEL: {

                                makeAndSendIntent(alarm, ACTION_RESERVED_CUSTOMER);
                                break;
                            }
                            case TYPE_PAY: {

                                makeAndSendIntent(alarm, ACTION_SCHEDULE_MGM);

                                break;
                            }
                            case TYPE_CANCEL_SCHEDULE: {

                                makeAndSendIntent(alarm, ACTION_RESERVATION_LIST);

                                break;
                            }
                            case TYPE_FAVORITE: {

                                makeAndSendIntent(alarm, ACTION_VIDEO);

                                break;
                            }
                            case TYPE_REVIEW: {

                                makeAndSendIntent(alarm, ACTION_VIDEO);

                                break;
                            }
                            case TYPE_CHATTING: {
//                            makeAndSendIntent(alarm, ACTION_CHAT_MESSAGE);
                                chatSend();

                                break;
                            }
                        }
                    }

                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<List<Alarm>>> request, int errorCode, String errorMessage, Throwable e) {

                }
            });
        }
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody, String action) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        switch (action) {
            case ACTION_RESERVATION_LIST: {
                intent.putExtra(SplashActivity.EXTRA_FRAGNAME, MainActivity.FRAG_RESERVATION_MGM);
                break;
            }
            case ACTION_RESERVED_CUSTOMER: {
                intent.putExtra(SplashActivity.EXTRA_FRAGNAME, MainActivity.FRAG_RESERVED_CUSTOMER);
                break;
            }
            case ACTION_SCHEDULE_MGM: {
                intent.putExtra(SplashActivity.EXTRA_FRAGNAME, MainActivity.FRAG_SCHEDULE_MGM);
                break;
            }
            case ACTION_VIDEO: {
                intent.putExtra(SplashActivity.EXTRA_FRAGNAME, MainActivity.FRAG_VIDEO);
                break;
            }
            case ACTION_CHATTING: {
                intent.putExtra(SplashActivity.EXTRA_FRAGNAME, ChattingActivity.class);
                break;
            }
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.manifest_ic_wedding_singers_512)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification(Alarm alarm, String action) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(SplashActivity.EXTRA_FRAGNAME, ChattingActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.manifest_ic_wedding_singers_512)
                .setContentTitle("FCM Message")
                .setContentText(alarm.getMessage())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    int dataId;

    private void makeAndSendIntent(Alarm alarm, String action){
        Intent intent = new Intent(action);
        mLBM.sendBroadcastSync(intent);
        boolean processed = intent.getBooleanExtra(EXTRA_RESULT, false);
        if (!processed) {
            sendNotification(alarm.getMessage(), action);
        }
        if(action.equals(ACTION_VIDEO) || action.equals(ACTION_SINGER_REVIEW)){
            dataId = alarm.getDataId();
            Log.i(TAG, "action.equals(ACTION_VIDEO) dataId : " + dataId);
        }
    }

    private void chatSend() {
        Log.i(TAG, "----- chatSend -----");
        long lastTime = DBManager.getInstance().getLastReceiveDate();
        Date date = new Date(lastTime);
        MessageListRequest request = new MessageListRequest(this, date);
        try {
            NetworkResult<List<ChatMessage>> result = NetworkManager.getInstance().getNetworkDataSync(request);
            List<ChatMessage> list = result.getResult();
            for (ChatMessage m : list) {

                DBManager.getInstance().addMessage(m.getSender(), ChatContract.ChatMessage.TYPE_RECEIVE, m.getMessage(), new Date());

                Intent i = new Intent(ACTION_CHAT_MESSAGE);
                i.putExtra(EXTRA_CHAT_USER, m.getSender());
                mLBM.sendBroadcastSync(i);
                boolean processed = i.getBooleanExtra(EXTRA_RESULT, false);
                if (!processed) {
                    sendNotification(m);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(ChatMessage m) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER, m.getSender());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.manifest_ic_wedding_singers_512)
                .setTicker("Chat Message")
                .setContentTitle("웨딩싱어즈 채팅 수신")
                .setContentText(m.getSender().getName() + "님의 메시지 - " + m.getMessage())
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
