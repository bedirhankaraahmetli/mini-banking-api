package com.banking.notification_service.service;

import java.math.BigDecimal;

import com.banking.mini_banking.grpc.NotificationRequest;
import com.banking.mini_banking.grpc.NotificationResponse;
import com.banking.mini_banking.grpc.NotificationServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Override
    public void sendTransferNotification(NotificationRequest request,
            StreamObserver<NotificationResponse> responseObserver) {

        // 1. Extract information from the mini-banking (Binary) request
        String accountNumber = request.getAccountNumber();
        double amount = request.getAmount();
        String message = request.getMessage();

        // 2. Process the notification (IRL, this could involve sending an email, SMS,
        // or push notification)
        System.out.println("=========================================");
        System.out.println(" [NEW NOTIFICATION - gRPC] ");
        System.out.println(" Account Number: " + accountNumber);
        System.out.println(" Amount: " + amount);
        System.out.println(" System Message: " + message);
        System.out.println("=========================================");

        // 3. Build the response to send back to the mini-banking service
        NotificationResponse response = NotificationResponse.newBuilder()
                .setSuccess(true)
                .setResultMessage("SMS sended to a customer successfully (Simulation)")
                .build();

        // 4. Send the response back to the mini-banking service
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
