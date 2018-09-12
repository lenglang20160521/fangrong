package DTO;

public class AuthInfo {

    /**
     * code : 0
     * data : {"tokenType":"bearer","accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEwMDAxLCJuYmYiOjE1MzYyOTk5MzksInJhdCI6MC42MzkwODA4NzUwODI5OTAyLCJyYWkiOjEyNDkxMDI4NDUsInR5cGUiOiJhcHAiLCJleHAiOjE1MzYzODYzMzksImp0aSI6IjAxNzNlYmViYTgzNTRkZjg5NmZkNzNkMDFmODA4MjdjIn0.B_C6rLOBcxnNo_PnH3q8dDbVVoFJhjoqjhuKkKgyxz615fBtUff6dbCPV5SezoO6rADWOvB_SL6SNf2jhZIC6Q","refreshToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEwMDAxLCJuYmYiOjE1MzYyOTk5MzksInJhdCI6MC4zNDU5MzM2MTgyODkyMTY4NywicmFpIjo2NTEyMDczMzksInR5cGUiOiJyZWYiLCJleHAiOjE1MzY5MDQ3MzksImp0aSI6IjAxNzNlYmViYTgzNTRkZjg5NmZkNzNkMDFmODA4MjdjIn0.qJNyhOvgOZ_En93tUdzrRmFfpyWSHnn8po4bnk5n3EuG7WskMN6eroQxPnRCeLi9PzptvCTQN91npTvHJRHxZw","expireAt":1536386339}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tokenType : bearer
         * accessToken : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEwMDAxLCJuYmYiOjE1MzYyOTk5MzksInJhdCI6MC42MzkwODA4NzUwODI5OTAyLCJyYWkiOjEyNDkxMDI4NDUsInR5cGUiOiJhcHAiLCJleHAiOjE1MzYzODYzMzksImp0aSI6IjAxNzNlYmViYTgzNTRkZjg5NmZkNzNkMDFmODA4MjdjIn0.B_C6rLOBcxnNo_PnH3q8dDbVVoFJhjoqjhuKkKgyxz615fBtUff6dbCPV5SezoO6rADWOvB_SL6SNf2jhZIC6Q
         * refreshToken : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEwMDAxLCJuYmYiOjE1MzYyOTk5MzksInJhdCI6MC4zNDU5MzM2MTgyODkyMTY4NywicmFpIjo2NTEyMDczMzksInR5cGUiOiJyZWYiLCJleHAiOjE1MzY5MDQ3MzksImp0aSI6IjAxNzNlYmViYTgzNTRkZjg5NmZkNzNkMDFmODA4MjdjIn0.qJNyhOvgOZ_En93tUdzrRmFfpyWSHnn8po4bnk5n3EuG7WskMN6eroQxPnRCeLi9PzptvCTQN91npTvHJRHxZw
         * expireAt : 1536386339
         */

        private String tokenType;
        private String accessToken;
        private String refreshToken;
        private int expireAt;

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public int getExpireAt() {
            return expireAt;
        }

        public void setExpireAt(int expireAt) {
            this.expireAt = expireAt;
        }
    }
}
