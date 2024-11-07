package solverz.business_card.domain.member.entity;

public enum LoginType {
    EMAIL("email"),
    GMAIL("gmail"),
    APPLE_MAIL("apple_mail");

    private String loginType;

    LoginType(String loginType)
    {
        this.loginType = loginType;
    }
    public String getLoginType() {
        return loginType;
    }
}
