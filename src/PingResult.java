public class PingResult {

    private String ipAddress ;
    private int resultCode ;

    public PingResult(String ipAddress, int resultCode) {
        this.ipAddress = ipAddress;
        this.resultCode = resultCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String toString() {
        return "PingResult [ipAddress=" + ipAddress + ", resultCode=" + resultCode + "]";
    }
    
}
