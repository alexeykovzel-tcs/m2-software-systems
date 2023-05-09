package ss.week3.hotel;

public class Safe {
    protected boolean isActivated;
    protected boolean isOpened;

    public Safe() {
        isActivated = false;
        isOpened = false;
    }

    public void activate() {
        isActivated = true;
    }

    public void deactivate() {
        isActivated = false;
    }

    public void open(){
        isOpened = true;
    }

    public void close(){
        isOpened = false;
    }

    public boolean isActive() {
        return isActivated;
    }

    public boolean isOpen() {
        return isOpened;
    }
}
