package bean;

/**
 * Dialogue_Text
 * 定义消息
 * */
public class Msg {
    public static final int jieshou=0;
    public static final int fasong=1;
    private String content;
    private int type;//消息类型

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
