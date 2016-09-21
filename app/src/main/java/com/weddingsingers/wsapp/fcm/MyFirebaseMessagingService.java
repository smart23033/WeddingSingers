/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
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
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.SplashActivity;
import com.weddingsingers.wsapp.data.Alarm;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.main.reservationmgm.ReservedCustomerFragment;
import com.weddingsingers.wsapp.manager.DBManager;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.AlarmListRequest;

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

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]

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

//        서버로부터 내가 받지 못한 푸시메시지 요청
        AlarmListRequest alarmListRequest = new AlarmListRequest(this);
        NetworkManager.getInstance().getNetworkData(alarmListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Alarm>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Alarm>>> request, NetworkResult<List<Alarm>> result) {
                    List<Alarm> list = result.getResult();

                for(Alarm a : list){
//                    TYPE에 따라 다른 액티비티로 보내는 인텐트 생성
                    String type = String.valueOf(a.getType());
                    switch (type){
                        case TYPE_RESERVE : {

                            break;
                        }
                        case TYPE_REJECT : {

                            break;
                        }
                        case TYPE_ACCEPT : {

                            break;
                        }
                        case TYPE_CANCEL : {

                            break;
                        }
                        case TYPE_PAY : {

                            break;
                        }
                        case TYPE_CANCEL_SCHEDULE : {

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
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
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
}
