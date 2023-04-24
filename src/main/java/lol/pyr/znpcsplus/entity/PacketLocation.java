package lol.pyr.znpcsplus.entity;

import com.github.retrooper.packetevents.util.Vector3d;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

public class PacketLocation {
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public PacketLocation(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public PacketLocation(Location location) {
        this(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public Location toBukkitLocation(World world) {
        return new Location(world, this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    public Vector3d toVector3d() {
        return new Vector3d(x, y, z);
    }

    private static final double _2PI = 2 * Math.PI;

    public Location lookingAt(Location loc) {
        return lookingAt(new PacketLocation(loc)).toBukkitLocation(loc.getWorld());
    }

    public PacketLocation lookingAt(PacketLocation loc) {
        final double x = loc.getX() - this.x;
        final double z = loc.getZ() - this.z;
        final double y = loc.getY() - this.y;

        if (x == 0 && z == 0) {
            return new PacketLocation(this.x, this.y, this.z, this.yaw, y > 0 ? -90 : 90);
        }

        double x2 = NumberConversions.square(x);
        double z2 = NumberConversions.square(z);
        double xz = Math.sqrt(x2 + z2);

        double theta = Math.atan2(-x, z);
        float yaw = (float) Math.toDegrees((theta + _2PI) % _2PI);
        float pitch = (float) Math.toDegrees(Math.atan(-y / xz));

        return new PacketLocation(this.x, this.y, this.z, yaw, pitch);
    }
}
