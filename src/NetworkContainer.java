import java.io.Serializable;
import java.net.InetAddress;

public class NetworkContainer implements Serializable {
    private NetworkType type;
    private Integer key;
    private Serializable data;
    private InetAddress target;

    public NetworkContainer(NetworkType type, Integer key, Serializable data) {
        this.type = type;
        this.key = key;
        this.data = data;
    }

    public NetworkContainer(NetworkType type, InetAddress target) {
        this.type = type;
        this.target = target;
    }

    public NetworkContainer(NetworkType type, Serializable data) {
        this.type = type;
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

    public InetAddress getTarget() {
        return target;
    }

    public void setTarget(InetAddress target) {
        this.target = target;
    }
}
