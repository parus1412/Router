import java.lang.*;

public class Network implements Cloneable {
    private IPv4Address address;
    private IPv4Address broadcastAddress;
    private IPv4Address firstUsableAddress;
    private IPv4Address lastUsableAddress;
    private long mask;
    private int maskLength;
    private String maskString;
    
    private static long validMaskLength (int maskLength) throws MyNetworkException {
        long mask = 4294967295L;
        long tmp;
        
        if ( maskLength > 32 || maskLength < 0 ) {
            throw new java.lang.IllegalArgumentException();
        }
        
        tmp = mask >> maskLength;
        mask = tmp ^ mask;
        
        return mask;
        
    }
    
    public Network(IPv4Address address, int maskLength) throws MyNetworkException {
        long fullMask = 4294967295L;
        this.mask = validMaskLength(maskLength);
        this.address = new IPv4Address(address.toLong() & mask);
        this.maskLength = maskLength;
        this.maskString = 
        ((mask >> 24 ) & 0xFF) + "." +
        ((mask >> 16 ) & 0xFF) + "." +
        ((mask >>  8 ) & 0xFF) + "." +
        ( mask        & 0xFF);
        this.broadcastAddress = new IPv4Address((fullMask - this.mask) + this.address.toLong());
        this.firstUsableAddress = new IPv4Address(this.address.toLong() + 1);
        this.lastUsableAddress = new IPv4Address(this.broadcastAddress.toLong() - 1);
    }
  
    public boolean contains(IPv4Address address) {
        return address.toLong() >= this.address.toLong() && address.toLong() <= this.broadcastAddress.toLong();
    }
    
    public IPv4Address getAddress() {
        return this.address;
    }
    
    public IPv4Address getBroadcastAddress() throws MyNetworkException {
        return this.broadcastAddress;
    }
    
    public IPv4Address getFirstUsableAddress() {
        return this.firstUsableAddress;
    }
    
    public IPv4Address getLastUsableAddress() {
        return this.lastUsableAddress;
    }
    
    public long getMask() {
        return this.mask;
    }
    
    public String getMaskString() {
        return this.maskString;
    }
    
    public int getMaskLength() {
        return this.maskLength;
    }
    
    public Network[] getSubnets() throws MyNetworkException {
        int tmpMaskLength = maskLength + 1;
        Network[] tmp = new Network[2];
        
        tmp[0] = new Network(new IPv4Address(this.address.toLong()), tmpMaskLength);
        tmp[1] = new Network(new IPv4Address(this.address.toLong() ^ (this.mask >> this.maskLength)), tmpMaskLength);
       
        return tmp;
    } 
    
    public long getTotalHosts() {
        long hosts = this.lastUsableAddress.toLong() - this.address.toLong();
        
        if ( maskLength == 32 ) {
            hosts = 1;
        }
        return hosts;
   }
   
    public boolean isPublic() throws MyNetworkException {
        Network public1 = new Network(new IPv4Address("10.0.0.0"), 8);
        Network public2 = new Network(new IPv4Address("172.16.0.0"), 12);
        Network public3 = new Network(new IPv4Address("192.168.0.0"), 16);
        
        if (public1.contains(this.address) || public2.contains(this.address) || public3.contains(this.address)) {
            return false;
        }

        return true;
    }
    
    public String toString() {
        return this.address.toString() + '/' + this.maskLength;
    }

}

// >>> 0xffffffff - 4294967294
// 1
// >>> 0xff000000
// 4278190080
// >>> 0xff
// 255
// >>> 0x0000ff00
// 65280
// >>> 0x0000ff00 >> 8
// 255
// >>> 0x00ff0000 >> 16
// 255
// >>> 
