package com.hocassian.people.domain.web;

/**
 * 功能描述：
 *
 * @author Hocassian
 * @date 2021-01-15 22:13
 */
public class ConnectWeb {

    private String connectWebId;

    private String connectWebName;

    private String connectWebInfo;

    private String personStoryIdAlpha;

    private String personStoryIdBeta;

    public String getConnectWebId() {
        return connectWebId;
    }

    public void setConnectWebId(String connectWebId) {
        this.connectWebId = connectWebId;
    }

    public String getConnectWebName() {
        return connectWebName;
    }

    public void setConnectWebName(String connectWebName) {
        this.connectWebName = connectWebName;
    }

    public String getConnectWebInfo() {
        return connectWebInfo;
    }

    public void setConnectWebInfo(String connectWebInfo) {
        this.connectWebInfo = connectWebInfo;
    }

    public String getPersonStoryIdAlpha() {
        return personStoryIdAlpha;
    }

    public void setPersonStoryIdAlpha(String personStoryIdAlpha) {
        this.personStoryIdAlpha = personStoryIdAlpha;
    }

    public String getPersonStoryIdBeta() {
        return personStoryIdBeta;
    }

    public void setPersonStoryIdBeta(String personStoryIdBeta) {
        this.personStoryIdBeta = personStoryIdBeta;
    }

    @Override
    public String toString() {
        return "ConnectWeb{" +
                "connectWebId='" + connectWebId + '\'' +
                ", connectWebName='" + connectWebName + '\'' +
                ", connectWebInfo='" + connectWebInfo + '\'' +
                ", personStoryIdAlpha='" + personStoryIdAlpha + '\'' +
                ", personStoryIdBeta='" + personStoryIdBeta + '\'' +
                '}';
    }
}
