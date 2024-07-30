package ru.skillfactory.mybankservice.api.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class BaseResponseDto<Data> {

    private String message;

    private Data data;

    private ResponseStatus status;

    public static BaseResponseDto asSuccess(Object data) {
        return builder()
                .message("OK")
                .data(data)
                .status(ResponseStatus.SUCCESS)
                .build();
    }

    public static BaseResponseDto asFailure(@NonNull String message) {
        if (message == null) {
            throw new NullPointerException("message is marked non-null but is null");
        } else {
            return builder()
                    .message(message)
                    .status(ResponseStatus.FAILURE)
                    .build();
        }
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
