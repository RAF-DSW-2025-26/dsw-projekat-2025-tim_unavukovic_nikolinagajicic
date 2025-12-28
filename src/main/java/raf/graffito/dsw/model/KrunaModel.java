package raf.graffito.dsw.model;

public class KrunaModel {
        private int x;
        private int y;
        private double scale;
        private double rotationAngle;

        public KrunaModel(int x, int y, double scale, double rotationAngle) {
                this.x = x;
                this.y = y;
                this.scale = scale;
                this.rotationAngle = rotationAngle;
        }

        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
        public double getScale() { return scale; }
        public void setScale(double scale) { this.scale = scale; }
        public double getRotationAngle() { return rotationAngle; }
        public void setRotationAngle(double rotationAngle) { this.rotationAngle = rotationAngle; }
}