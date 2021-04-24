import java.io.Serializable;

public class NetworkContainer implements Serializable {
    private NetworkType type;
    private Integer key;
    private Serializable data;

    public NetworkContainer(NetworkType type, Integer key, Serializable data) {
        this.type = type;
        this.key = key;
        this.data = data;
    }

    public NetworkType getType() {
        return type;
    }

    public void setType(NetworkType type) {
        this.type = type;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
}
