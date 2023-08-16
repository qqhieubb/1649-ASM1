public class Message {
    private String content;
    private boolean isRead;

    public Message(String content) {
        this.content = content;
        this.isRead = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        isRead = true;
    }

    @Override
    public String toString() {
        return isRead ? content : "Unread";
    }
}
