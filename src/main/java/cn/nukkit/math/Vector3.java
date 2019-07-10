package cn.nukkit.math;

import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import org.jetbrains.annotations.NotNull;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Vector3 implements Cloneable {
    public static final Vector3 ORIGIN = new Vector3(0, 0, 0);

    public double x;
    public double y;
    public double z;

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(double x) {
        this(x, 0, 0);
    }

    public Vector3(double x, double y) {
        this(x, y, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 ofPolarCoordinate(double yawDegrees, double pitchDegrees) {
        return new Vector3().setPolarComponents(yawDegrees, pitchDegrees);
    }

    public static Vector3 ofPolarCoordinate(double yawDegrees, double pitchDegrees, double length) {
        return new Vector3().setPolarComponents(yawDegrees, pitchDegrees, length);
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

    public int getFloorX() {
        return (int) Math.floor(this.x);
    }

    public int getFloorY() {
        return (int) Math.floor(this.y);
    }

    public int getFloorZ() {
        return (int) Math.floor(this.z);
    }

    public int getChunkX() {
        return getFloorX() >> 4;
    }

    public int getChunkZ() {
        return getFloorZ() >> 4;
    }

    public double getRight() {
        return this.x;
    }

    public double getUp() {
        return this.y;
    }

    public double getForward() {
        return this.z;
    }

    public double getSouth() {
        return this.x;
    }

    public double getWest() {
        return this.z;
    }

    /**
     * 绕 {@linkplain #ORIGIN 坐标原点} 旋转这个向量(旋转绝对角度)
     *
     * @param deltaYawDegrees   yaw 旋转量, 角度制, 0~360
     * @param deltaPitchDegrees pitch 旋转量, 角度制, 0~180
     * @return new V3
     */
    public Vector3 rotate(double deltaYawDegrees, double deltaPitchDegrees) {
        return Vector3.ofPolarCoordinate(
                ORIGIN.getYawTo(this) + deltaYawDegrees,
                ORIGIN.getPitchTo(this) + deltaPitchDegrees,
                length()
        );
    }

    /**
     * 向前移动点 <br/>
     * distance 是精准的.
     */
    public Vector3 goStraight(double distance) {
        return Vector3.ofPolarCoordinate(
                ORIGIN.getYawTo(this),
                ORIGIN.getPitchTo(this),
                distance
        ).addSelf(this);
    }

    /**
     * 向前移动点 <br/>
     * distance 是精准的.
     */
    public Vector3 goStraightSelf(double distance) {
        return this.addPolarSelf(
                ORIGIN.getYawTo(this),
                ORIGIN.getPitchTo(this),
                distance
        );
    }

    /**
     * 绕 {@linkplain #ORIGIN 坐标原点} 旋转这个向量(旋转绝对角度)
     *
     * @param deltaYaw   yaw 旋转量, 角度制, 0~360
     * @param deltaPitch pitch 旋转量, 角度制, 0~180
     * @return new V3
     */
    public Vector3 rotateSelf(double deltaYaw, double deltaPitch) {
        return this.setPolarComponents(
                ORIGIN.getYawTo(this) + deltaYaw,
                ORIGIN.getPitchTo(this) + deltaPitch,
                length()
        );
    }

    /**
     * 绕轴旋转这个向量. 罗德里格旋转公式
     *
     * @param axis          轴
     * @param rotateRadians 弧度制
     * @return new V3
     */
    @SuppressWarnings("Duplicates")
    public Vector3 rotate(Vector3 axis, double rotateRadians) {
        return new Vector3(
                (axis.x * axis.x * (1 - MathHelper.cos(rotateRadians)) + MathHelper.cos(rotateRadians)) * this.x + (axis.x * axis.y * (1 - MathHelper.cos(rotateRadians)) - axis.z * MathHelper.sin(rotateRadians)) * this.y + (axis.x * axis.z * (1 - MathHelper.cos(rotateRadians)) + axis.y * MathHelper.sin(rotateRadians)) * this.z,
                (axis.x * axis.y * (1 - MathHelper.cos(rotateRadians)) + axis.z * MathHelper.sin(rotateRadians)) * this.x + (axis.y * axis.y * (1 - MathHelper.cos(rotateRadians)) + MathHelper.cos(rotateRadians)) * this.y + (axis.y * axis.z * (1 - MathHelper.cos(rotateRadians)) - axis.x * MathHelper.sin(rotateRadians)) * this.z,
                (axis.x * axis.z * (1 - MathHelper.cos(rotateRadians)) - axis.y * MathHelper.sin(rotateRadians)) * this.x + (axis.y * axis.z * (1 - MathHelper.cos(rotateRadians)) + axis.x * MathHelper.sin(rotateRadians)) * this.y + (axis.z * axis.z * (1 - MathHelper.cos(rotateRadians)) + MathHelper.cos(rotateRadians)) * this.z
        );
    }

    /**
     * 绕轴旋转这个向量. 罗德里格旋转公式
     *
     * @param axis          轴
     * @param rotateRadians 弧度制
     * @return this
     */
    @SuppressWarnings("Duplicates")
    public Vector3 rotateSelf(Vector3 axis, double rotateRadians) {
        double cos = MathHelper.cos(rotateRadians);
        double sin = MathHelper.sin(rotateRadians);

        double newX = (axis.x * axis.x * (1 - cos) + cos) * this.x +
                (axis.x * axis.y * (1 - cos) - axis.z * sin) * this.y +
                (axis.x * axis.z * (1 - cos) + axis.y * sin) * this.z;
        double newY = (axis.x * axis.y * (1 - cos) + axis.z * sin) * this.x +
                (axis.y * axis.y * (1 - cos) + cos) * this.y +
                (axis.y * axis.z * (1 - cos) - axis.x * sin) * this.z;
        double newZ = (axis.x * axis.z * (1 - cos) - axis.y * sin) * this.x +
                (axis.y * axis.z * (1 - cos) + axis.x * sin) * this.y +
                (axis.z * axis.z * (1 - cos) + cos) * this.z;
        return this.setComponents(newX, newY, newZ);
    }

    /**
     * 得到一个与 this 垂直的向量
     */
    public Vector3 getVerticalVector3() {
        int flag = 0x000;
        if (this.x == 0) {
            flag |= 0x100;
        }
        if (this.y == 0) {
            flag |= 0x010;
        }
        if (this.z == 0) {
            flag |= 0x001;
        }

        switch (flag) {
            case 0x010:
            case 0x000:
                return new Vector3(1, 0, -this.x / this.z);

            case 0x100:
                return new Vector3(0, 1, -this.y / this.z);

            case 0x001:
                return new Vector3(-this.y / this.x, 1, 0);

            case 0x110:
            case 0x101:
            case 0x011:
            case 0x111://2到三个坐标值为0, 不予计算
            default:
                return new Vector3();
        }

        /*
        if (this.z != 0) {
            if (this.x != 0) {
                return new Vector3(1, 0, -this.x / this.z);
            } else if (this.y != 0) {
                return new Vector3(0, 1, -this.y / this.z);
            } else {
                return new Vector3();
            }
        } else if (this.y != 0) {
            return new Vector3(1, -(this.x + this.z) / this.y, 1);
        } else if (this.x != 0) {
            return new Vector3(-(this.y + this.z) / this.x, 1, 1);
        } else {
            return new Vector3();
        }*/
    }

    public Vector3 add(double x) {
        return this.add(x, 0, 0);
    }

    public Vector3 add(double x, double y) {
        return this.add(x, y, 0);
    }

    public Vector3 add(double x, double y, double z) {
        return new Vector3(this.x + x, this.y + y, this.z + z);
    }

    public Vector3 addPolar(double yaw, double pitch) {
        return Vector3.ofPolarCoordinate(yaw, pitch).addSelf(this);
    }

    public Vector3 addPolar(double yaw, double pitch, double distance) {
        return Vector3.ofPolarCoordinate(yaw, pitch, distance).addSelf(this);
    }

    public Vector3 addPolarSelf(double yaw, double pitch) {
        double x = this.x;
        double y = this.y;
        double z = this.z;
        return this.setPolarComponents(yaw, pitch).addSelf(x, y, z);
    }

    public Vector3 addPolarSelf(double yaw, double pitch, double distance) {
        double x = this.x;
        double y = this.y;
        double z = this.z;
        return this.setPolarComponents(yaw, pitch, distance).addSelf(x, y, z);
    }

    public Vector3 addSelf(Vector3 x) {
        this.x += x.getX();
        this.y += x.getY();
        this.z += x.getZ();
        return this;
    }

    public Vector3 addSelf(double x) {
        this.x += x;
        return this;
    }

    public Vector3 addSelf(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector3 addSelf(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3 add(Vector3 x) {
        return new Vector3(this.x + x.getX(), this.y + x.getY(), this.z + x.getZ());
    }

    public Vector3 subtract() {
        return this.subtract(0, 0, 0);
    }

    public Vector3 subtract(double x) {
        return this.subtract(x, 0, 0);
    }

    public Vector3 subtract(double x, double y) {
        return this.subtract(x, y, 0);
    }

    public Vector3 subtract(double x, double y, double z) {
        return this.add(-x, -y, -z);
    }

    public Vector3 subtractSelf(Vector3 x) {
        return this.add(-x.getX(), -x.getY(), -x.getZ());
    }

    public Vector3 subtractSelf() {
        return this.subtractSelf(0, 0, 0);
    }

    public Vector3 subtractSelf(double x) {
        return this.subtractSelf(x, 0, 0);
    }

    public Vector3 subtractSelf(double x, double y) {
        return this.subtractSelf(x, y, 0);
    }

    public Vector3 subtractSelf(double x, double y, double z) {
        return this.addSelf(-x, -y, -z);
    }

    public Vector3 subtract(Vector3 x) {
        return this.add(-x.getX(), -x.getY(), -x.getZ());
    }

    public Vector3 multiply(double number) {
        return new Vector3(this.x * number, this.y * number, this.z * number);
    }

    public Vector3 divide(double number) {
        return new Vector3(this.x / number, this.y / number, this.z / number);
    }

    public Vector3 multiplySelf(double number) {
        this.x *= number;
        this.y *= number;
        this.z *= number;
        return this;
    }

    public Vector3 divideSelf(double number) {
        this.x /= number;
        this.y /= number;
        this.z /= number;
        return this;
    }

    public Vector3 ceil() {
        return new Vector3((int) Math.ceil(this.x), (int) Math.ceil(this.y), (int) Math.ceil(this.z));
    }

    public Vector3 floor() {
        return new Vector3(this.getFloorX(), this.getFloorY(), this.getFloorZ());
    }

    public Vector3 round() {
        return new Vector3(Math.round(this.x), Math.round(this.y), Math.round(this.z));
    }

    public Vector3 abs() {
        return new Vector3((int) Math.abs(this.x), (int) Math.abs(this.y), (int) Math.abs(this.z));
    }

    public Vector3 getSide(BlockFace face) {
        return this.getSide(face, 1);
    }

    public Vector3 getSide(BlockFace face, int step) {
        return new Vector3(this.getX() + face.getXOffset() * step, this.getY() + face.getYOffset() * step, this.getZ() + face.getZOffset() * step);
    }

    public Vector3 up() {
        return up(1);
    }

    public Vector3 up(int step) {
        return getSide(BlockFace.UP, step);
    }

    public Vector3 down() {
        return down(1);
    }

    public Vector3 down(int step) {
        return getSide(BlockFace.DOWN, step);
    }

    public Vector3 north() {
        return north(1);
    }

    public Vector3 north(int step) {
        return getSide(BlockFace.NORTH, step);
    }

    public Vector3 south() {
        return south(1);
    }

    public Vector3 south(int step) {
        return getSide(BlockFace.SOUTH, step);
    }

    public Vector3 east() {
        return east(1);
    }

    public Vector3 east(int step) {
        return getSide(BlockFace.EAST, step);
    }

    public Vector3 west() {
        return west(1);
    }

    public Vector3 west(int step) {
        return getSide(BlockFace.WEST, step);
    }

    public double distance(Vector3 pos) {
        return Math.sqrt(this.distanceSquared(pos));
    }

    public double distanceSquared(Vector3 pos) {
        return MathHelper.square(this.x - pos.x) + MathHelper.square(this.y - pos.y) + MathHelper.square(this.z - pos.z);
    }

    /**
     * 水平(不考虑 y) distance
     */
    public double distanceHorizontal(Vector3 pos) {
        return Math.sqrt(distanceHorizontalSquared(pos));
    }

    public double distanceHorizontalSquared(Vector3 pos) {
        return MathHelper.square(this.x - pos.x) + MathHelper.square(this.z - pos.z);
    }

    public double maxPlainDistance() {
        return this.maxPlainDistance(0, 0);
    }

    public double maxPlainDistance(double x) {
        return this.maxPlainDistance(x, 0);
    }

    public double maxPlainDistance(double x, double z) {
        return Math.max(Math.abs(this.x - x), Math.abs(this.z - z));
    }

    public double maxPlainDistance(Vector2 vector) {
        return this.maxPlainDistance(vector.x, vector.y);
    }

    public double maxPlainDistance(Vector3 x) {
        return this.maxPlainDistance(x.x, x.z);
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vector3 normalize() {
        double len = this.lengthSquared();
        if (len > 0) {
            return this.divide(Math.sqrt(len));
        }
        return new Vector3(0, 0, 0);
    }

    public double dot(Vector3 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector3 cross(Vector3 v) {
        return new Vector3(
                this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x
        );
    }

    /**
     * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     *
     * @param v vector
     * @param x x value
     * @return intermediate vector
     */
    public Vector3 getIntermediateWithXValue(Vector3 v, double x) {
        double xDiff = v.x - this.x;
        double yDiff = v.y - this.y;
        double zDiff = v.z - this.z;
        if (xDiff * xDiff < 0.0000001) {
            return null;
        }
        double f = (x - this.x) / xDiff;
        if (f < 0 || f > 1) {
            return null;
        } else {
            return new Vector3(this.x + xDiff * f, this.y + yDiff * f, this.z + zDiff * f);
        }
    }

    /**
     * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     *
     * @param v vector
     * @param y y value
     * @return intermediate vector
     */
    public Vector3 getIntermediateWithYValue(Vector3 v, double y) {
        double xDiff = v.x - this.x;
        double yDiff = v.y - this.y;
        double zDiff = v.z - this.z;
        if (yDiff * yDiff < 0.0000001) {
            return null;
        }
        double f = (y - this.y) / yDiff;
        if (f < 0 || f > 1) {
            return null;
        } else {
            return new Vector3(this.x + xDiff * f, this.y + yDiff * f, this.z + zDiff * f);
        }
    }

    /**
     * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     *
     * @param v vector
     * @param z z value
     * @return intermediate vector
     */
    public Vector3 getIntermediateWithZValue(Vector3 v, double z) {
        double xDiff = v.x - this.x;
        double yDiff = v.y - this.y;
        double zDiff = v.z - this.z;
        if (zDiff * zDiff < 0.0000001) {
            return null;
        }
        double f = (z - this.z) / zDiff;
        if (f < 0 || f > 1) {
            return null;
        } else {
            return new Vector3(this.x + xDiff * f, this.y + yDiff * f, this.z + zDiff * f);
        }
    }

    /**
     * 即获取 this 对 another 的 yaw, in degree
     */
    @SuppressWarnings("Duplicates")
    public double getYawTo(@NotNull Vector3 another) {
        double deltaX = another.getX() - this.getX();
        double deltaZ = another.getZ() - this.getZ();

        if (deltaX == 0 && deltaZ == 0) {
            return 0;
        }

        double yaw = Math.toDegrees(Math.asin(deltaX / Math.sqrt(deltaX * deltaX + deltaZ * deltaZ)));

        if (yaw < 0) {
            yaw = 360d + yaw;
        }

        return yaw;
    }

    /**
     * 即获取 this 对 another 的 pitch, in degree
     */
    @SuppressWarnings("Duplicates")
    public double getPitchTo(@NotNull Vector3 another) {
        double deltaX = another.getX() - this.getX();
        double deltaY = another.getY() - this.getY();
        double deltaZ = another.getZ() - this.getZ();
        return (double) Math.round(Math.toDegrees(Math.asin(deltaY / Math.sqrt(deltaX * deltaX + deltaZ * deltaZ + deltaY * deltaY))));
    }

    /**
     * 即获取 this 对 another 的 pitch, in degree
     */
    @SuppressWarnings("Duplicates")
    public double getPositivePitchTo(@NotNull Vector3 another) {
        double pitch = getPitchTo(another);
        pitch %= 180;
        return pitch < 0 ? 360 - -pitch : pitch;
    }

    public Vector3 setComponents(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * 通过极坐标参数设置.
     *
     * @param yawDegrees   yawDegrees
     * @param pitchDegrees 只要是 pitch 就可以. 自动转换
     * @param length       长度
     * @return this
     */
    public Vector3 setPolarComponents(double yawDegrees, double pitchDegrees, double length) {
        yawDegrees %= 360;

        pitchDegrees %= 180;
        pitchDegrees = pitchDegrees < 0 ? 360 - -pitchDegrees : pitchDegrees;

        yawDegrees = Math.toRadians(yawDegrees);
        pitchDegrees = Math.toRadians(pitchDegrees);
        this.x = length * MathHelper.sin(yawDegrees) * MathHelper.cos(pitchDegrees);
        this.y = length * MathHelper.sin(pitchDegrees);
        this.z = length * MathHelper.cos(yawDegrees) * MathHelper.cos(pitchDegrees);

        //this.x = length * Math.cos(yawDegrees) * Math.sin(pitchDegrees);
        //this.y = length * Math.cos(pitchDegrees);
        //this.z = length * Math.sin(yawDegrees) * Math.sin(pitchDegrees);

        //this.x = length * Math.sin(yawDegrees) * Math.sin(pitchDegrees);
        //this.y = length * Math.cos(pitchDegrees);
        //this.z = length * Math.cos(yawDegrees) * Math.sin(pitchDegrees);

        //his.x = length * Math.cos(yawDegrees) * Math.cos(pitchDegrees);
        //his.y = length * Math.sin(pitchDegrees);
        //his.z = length * Math.sin(yawDegrees) * Math.cos(pitchDegrees);
        return this;
    }

    public Vector3 setPolarComponents(double yawDegrees, double positivePitchDegrees) {
        return this.setPolarComponents(yawDegrees, positivePitchDegrees, 1);
    }

    public Vector3 setComponents(Vector3 vector3) {
        this.x = vector3.x;
        this.y = vector3.y;
        this.z = vector3.z;
        return this;
    }

    @Override
    public String toString() {
        return "Vector3(x=" + this.x + ",y=" + this.y + ",z=" + this.z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3)) {
            return false;
        }

        Vector3 other = (Vector3) obj;

        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    @Override
    public int hashCode() {
        return ((int) x ^ ((int) z << 12)) ^ ((int) y << 24);
    }

    public int rawHashCode() {
        return super.hashCode();
    }

    @Override
    public Vector3 clone() {
        try {
            return (Vector3) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Vector3 asVector3() {
        if (this.getClass() == Vector3.class
                || this.getClass() == Position.class
                || this.getClass() == Location.class) {
            return this;
        }

        return new Vector3(x, y, z);
    }

    public Vector3 forceAsVector3() {
        return new Vector3(x, y, z);
    }

    /**
     * 转化单位向量
     */
    public Vector3 asUnitVector3() {
        double length = length();
        if (length == 0) {
            return this;
        }
        return new Vector3(x / length, y / length, z / length);
    }

    public Vector3f asVector3f() {
        return new Vector3f((float) this.x, (float) this.y, (float) this.z);
    }

    public BlockVector3 asBlockVector3() {
        return new BlockVector3(this.getFloorX(), this.getFloorY(), this.getFloorZ());
    }
}
