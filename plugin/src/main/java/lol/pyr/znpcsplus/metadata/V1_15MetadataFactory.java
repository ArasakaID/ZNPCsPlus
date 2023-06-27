package lol.pyr.znpcsplus.metadata;

import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import lol.pyr.znpcsplus.util.Vector3f;

public class V1_15MetadataFactory extends V1_14MetadataFactory {
    @Override
    public EntityData armorStandProperties(boolean small, boolean arms, boolean noBasePlate) {
        return newEntityData(14, EntityDataTypes.BYTE, (byte) ((small ? 0x01 : 0) | (arms ? 0x04 : 0) | (!noBasePlate ? 0x08 : 0)));
    }

    @Override
    public EntityData armorStandHeadRotation(Vector3f headRotation) {
        return newEntityData(15, EntityDataTypes.ROTATION, new com.github.retrooper.packetevents.util.Vector3f(headRotation.getX(), headRotation.getY(), headRotation.getZ()));
    }

    @Override
    public EntityData armorStandBodyRotation(Vector3f bodyRotation) {
        return newEntityData(16, EntityDataTypes.ROTATION, new com.github.retrooper.packetevents.util.Vector3f(bodyRotation.getX(), bodyRotation.getY(), bodyRotation.getZ()));
    }

    @Override
    public EntityData armorStandLeftArmRotation(Vector3f leftArmRotation) {
        return newEntityData(17, EntityDataTypes.ROTATION, new com.github.retrooper.packetevents.util.Vector3f(leftArmRotation.getX(), leftArmRotation.getY(), leftArmRotation.getZ()));
    }

    @Override
    public EntityData armorStandRightArmRotation(Vector3f rightArmRotation) {
        return newEntityData(18, EntityDataTypes.ROTATION, new com.github.retrooper.packetevents.util.Vector3f(rightArmRotation.getX(), rightArmRotation.getY(), rightArmRotation.getZ()));
    }

    @Override
    public EntityData armorStandLeftLegRotation(Vector3f leftLegRotation) {
        return newEntityData(19, EntityDataTypes.ROTATION, new com.github.retrooper.packetevents.util.Vector3f(leftLegRotation.getX(), leftLegRotation.getY(), leftLegRotation.getZ()));
    }

    @Override
    public EntityData armorStandRightLegRotation(Vector3f rightLegRotation) {
        return newEntityData(20, EntityDataTypes.ROTATION, new com.github.retrooper.packetevents.util.Vector3f(rightLegRotation.getX(), rightLegRotation.getY(), rightLegRotation.getZ()));
    }
}
