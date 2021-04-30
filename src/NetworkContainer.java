import java.io.Serializable;
import java.net.InetAddress;

/**
 * NetworkContainer is used to store all game object in before sending between the Client and Server
 */
public class NetworkContainer implements Serializable {
    /**
     * Determines what sort of actions the Client and Server need to take in response to the container
     */
    private NetworkType type;
    /**
     * Used to distinguish which object in a list of them we wish to interact with
     */
    private Integer key;
    /**
     * The bulk of the objects are sent in this field.
     */
    private Serializable data;
    /**
     * If there is a use case in which another object is needed to be sent this field is used. Currently for
     * communicating token creation.
     */
    private Serializable auxData;
    /**
     * If this NetworkContainer is for a Specific client this can be used to record which Client that is
     */
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

    public NetworkContainer(NetworkType type, Integer key) {
        this.type = type;
        this.key = key;
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

    public Serializable getAuxData() {
        return auxData;
    }

    public void setAuxData(Serializable auxData) {
        this.auxData = auxData;
    }
}
