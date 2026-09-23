package model;

public class OrderResponse {
    private int track;

    public OrderResponse(int track) {
        this.track = track;
    }

    public OrderResponse() {
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }
}
