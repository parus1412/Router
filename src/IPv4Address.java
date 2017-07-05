import java.lang.*;
import java.lang.IllegalArgumentException;

public class IPv4Address implements Cloneable {
    private String ipString; 
    private long ipInt;
    
    private static String[] validStringIPv4(String ip) throws java.lang.IllegalArgumentException {
        String[] parts = ip.split( "\\." );
        
        if (ip == null || ip.isEmpty()) {
           throw new IllegalArgumentException();
        } else if (parts.length != 4) {
            throw new IllegalArgumentException();
        } 
        
        for ( int i = 0; i < 4; i++ ) {
            int num = Integer.parseInt( parts[i] );
            
            if ( (num < 0) || (num > 255) ) {
                throw new IllegalArgumentException();
            }
        }
        return parts;
    }
    
    private static long validLongIPv4(long ip) throws IllegalArgumentException {
        long max = 4294967295L;
        
        if (ip > max || ip < 0) {
           throw new IllegalArgumentException();
        }
        return ip;
    }

    public IPv4Address(String address) throws IllegalArgumentException {
        String[] parts = validStringIPv4(address);
        this.ipString = address;
        this.ipInt = 0;
        
        for (int i = 0, octet = 8; i < 4; ++i, octet += 8) {
            this.ipInt += Long.parseLong(parts[i]) << (32-octet);
        }
    }
    
    public IPv4Address(long address) throws IllegalArgumentException {
        this.ipInt = validLongIPv4(address);
        this.ipString = 
        ((address >> 24 ) & 0xFF) + "." +
        ((address >> 16 ) & 0xFF) + "." +
        ((address >>  8 ) & 0xFF) + "." +
        ( address         & 0xFF);
    }

    public boolean lessThan(IPv4Address address) {
    	return this.ipInt < address.ipInt;
    }
    
    public boolean greaterThan(IPv4Address address) {
    	return this.ipInt > address.ipInt;
    }
    
    public boolean equals(IPv4Address address) {
        return this.ipInt == address.ipInt;
    }

    public String toString() {
        return this.ipString;
    }
    
    public long toLong() {
        return this.ipInt;

    }
}
