package androidx.constraintlayout.solver.widgets;

public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f27x;

    /* renamed from: y */
    public int f28y;

    public void setBounds(int x, int y, int width2, int height2) {
        this.f27x = x;
        this.f28y = y;
        this.width = width2;
        this.height = height2;
    }

    /* access modifiers changed from: package-private */
    public void grow(int w, int h) {
        this.f27x -= w;
        this.f28y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    /* access modifiers changed from: package-private */
    public boolean intersects(Rectangle bounds) {
        return this.f27x >= bounds.f27x && this.f27x < bounds.f27x + bounds.width && this.f28y >= bounds.f28y && this.f28y < bounds.f28y + bounds.height;
    }

    public boolean contains(int x, int y) {
        return x >= this.f27x && x < this.f27x + this.width && y >= this.f28y && y < this.f28y + this.height;
    }

    public int getCenterX() {
        return (this.f27x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f28y + this.height) / 2;
    }
}
