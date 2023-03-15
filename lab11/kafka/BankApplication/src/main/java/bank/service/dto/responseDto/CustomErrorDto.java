package bank.service.dto.responseDto;

public class CustomErrorDto {
    private String errorMessage;

    public CustomErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
