package cn.nukkit.math;

import org.junit.jupiter.api.Test;

/**
 * @author Him188moe @ NukkitX project
 */
class Vector3Test {


    @Test
    void setPolarComponents() {
        var v3 = Vector3.ofPolarCoordinate(45, -45, 1);
        System.out.println(Vector3.ORIGIN.getYawTo(v3));
        System.out.println(Vector3.ORIGIN.getPitchTo(v3));

        System.out.println(v3);
        System.out.println("v3.length() = " + v3.length());
    }


    @Test
    void rotate() {
        for (int i = 0; i < 10; i++) {
            Vector3 v3 = new Vector3(Math.random(), Math.random(), Math.random()).asUnitVector3();
            System.out.println(Vector3.ofPolarCoordinate(Vector3.ORIGIN.getYawTo(v3), Vector3.ORIGIN.getPitchTo(v3)).asUnitVector3().subtract(v3));
            System.out.println(v3.subtract(v3.rotate(0, 0)));
            System.out.println(v3.dot(v3.rotate(0, 90)));
            System.out.println();
        }
    }

    @Test
    boolean rotate2() {
        Vector3 vector3 = new Vector3(1, -1, 1);

        System.out.println("Vector3.ORIGIN.getYawTo(vector3) = " + Vector3.ORIGIN.getYawTo(vector3));
        System.out.println("Vector3.ORIGIN.getPitchTo(vector3) = " + Vector3.ORIGIN.getPitchTo(vector3));
        //Vector3 rot = vector3.rotate(new Vector3(1, 0, 1), Math.toRadians(90));
        Vector3 rot = vector3.rotate(90, 0);
        System.out.println(rot);
        System.out.println(vector3.dot(rot));

        System.out.println(vector3.getXZVerticalVector3().dot(vector3));
        return true;
    }
}