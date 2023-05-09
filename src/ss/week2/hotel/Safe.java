package ss.week2.hotel;

public class Safe {
    private boolean isActivated;
    private boolean isOpened;

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
