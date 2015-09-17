/*
 * Copyright (C) 2002-2007 Berthold Firmenich
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, look at "http://www.gnu.org/copyleft/gpl.html" or
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 *
 * Contact address: berthold.firmenich@cademia.org
 * Homepage       : http://www.cademia.org/
 */

package util;

import java.awt.geom.Point2D;

/**
 * The Vector2D class defines a vector (x, y) in coordinate space.
 *
 * @author Berthold Firmenich
 * @version $Revision: 1.5 $, $Author: cvs-richte10 $
 * @version $Date: 2006/12/18 16:19:21 $
 */
public class Vector2D extends Point2D implements Cloneable {

    static void main(String[] args) throws NullVectorException {
        System.out.println("getAngleCCW(xvec, yvec) = " +
                X_UNIT.getAngleCCW(Y_UNIT) * 180. / Math.PI);
        System.out.println("getAngleCCW(yvec, xvec) = " +
                Y_UNIT.getAngleCCW(X_UNIT) * 180. / Math.PI);
        System.out.println("getAngleCCW(xvec, xvec) = " +
                X_UNIT.getAngleCCW(X_UNIT) * 180. / Math.PI);

        System.out.println("getAngleSmallest(xvec, yvec) = " +
                X_UNIT.getAngleSmallest(Y_UNIT) * 180. / Math.PI);
        System.out.println("getAngleSmallest(yvec, xvec) = " +
                Y_UNIT.getAngleSmallest(X_UNIT) * 180. / Math.PI);
        System.out.println("getAngleSmallest(xvec, xvec) = " +
                X_UNIT.getAngleSmallest(X_UNIT) * 180. / Math.PI);

        Vector2D vec = X_UNIT;
        for (int i = 0; i < 12; i++) {
            vec.rotate(Math.PI / 6.);
            System.out.println("vec.rotate(Math.PI / 6.): " +
                    vec.x + ", " + vec.y);
        }
    }

    // =========================================================================
    // Attributes ==============================================================
    // =========================================================================

    /**
     * The x coordinate of this <code>Vector2D</code>.
     */
    public double x = 0;

    /**
     * The y coordinate of this <code>Vector2D</code>.
     */
    public double y = 0;

    // =========================================================================
    // Constants ===============================================================
    // =========================================================================

    /**
     * The x unit vector constant (1, 0).
     */
    public static final Vector2D X_UNIT = new Vector2D(1, 0);

    /**
     * The y unit vector constant (0, 1).
     */
    public static final Vector2D Y_UNIT = new Vector2D(0, 1);

    // =========================================================================
    // Constructors ============================================================
    // =========================================================================

    /**
     * Constructs a null vector.
     */
    public Vector2D() {
    }

    /**
     * Constructs a vector (x, y).
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a vector from pnt1 to pnt2.
     * @param pnt1 the point
     */
    public Vector2D(Point2D pnt1, Point2D pnt2) {
        this.x = pnt2.getX() - pnt1.getX();
        this.y = pnt2.getY() - pnt1.getY();
    }

    // =========================================================================
    // Accessors ===============================================================
    // =========================================================================

    /**
     * Returns the x coordinate of this <code>Vector2D</code>.
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of this <code>Vector2D</code>.
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    // =========================================================================
    // Manipulators ============================================================
    // =========================================================================

    /**
     * Sets the coordinates of this <code>Vector2D</code>.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the coordinates of this <code>Vector2D</code> from the location of
     * the pnt <code>Point2D</code>.
     * @param pnt the vector
     */
    public void setLocation(Point2D pnt) {
        this.x = pnt.getX();
        this.y = pnt.getY();
    }

    /**
     * Sets this <code>Vector2D</code> as vector from <code>pnt1</code> to
     * <code>pnt2</code>.
     * @param pnt1 the 1st point
     * @param pnt2 the 2nd point
     */
    public void setLocation(Point2D pnt1, Point2D pnt2) {
        this.x = pnt2.getX() - pnt1.getX();
        this.y = pnt2.getY() - pnt1.getY();
    }

    // =========================================================================
    // Object protocol =========================================================
    // =========================================================================

    /**
     * Creates a new object of the same class and with the same contents as this
     * object.
     * @return     a clone of this instance.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        java.lang.Cloneable
     */
    public Object clone() {
        return super.clone();
    }

    /**
     * Returns the hashcode for this <code>Point2D</code>.
     * @return      a hash code for this <code>Point2D</code>.
     */
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(getX());
        bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Determines whether or not two points are equal. Two instances of
     * <code>Point2D</code> are equal if the values of their
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Point2D</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Point2D</code> and has
     *         the same values; <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Vector2D) {
            Vector2D v2d = (Vector2D) obj;
            return (getX() == v2d.getX()) && (getY() == v2d.getY());
        }
        return super.equals(obj);
    }

    // =========================================================================
    // Vector algebra ==========================================================
    // =========================================================================

    /**
     * Rotates this <code>Vector2D</code> by PI/2.
     * @return this <code>Vector2D</code>
     */
    public Vector2D right() {
        double _x = this.x;
        this.x = this.y;
        this.y = - _x;
        return this;
    }

    /**
     * Rotates this <code>Vector2D</code> by - PI/2.
     * @return this <code>Vector2D</code>
     */
    public Vector2D left() {
        double _x = this.x;
        this.x = - this.y;
        this.y = _x;
        return this;
    }

    /**
     * Rotates this <code>Vector2D</code> by an angle.
     * @param ang the angle in radians
     * @return this <code>Vector2D</code>
     */
    public Vector2D rotate(double ang) {
        double cos = Math.cos(-ang);
        double sin = Math.sin(-ang);
        double _x = this.x;
        this.x = this.x * cos + this.y * sin;
        this.y =   - _x * sin + this.y * cos;
        return this;
    }

    /**
     * Normalizes this <code>Vector2D</code>.
     * @return this <code>Vector2D</code>
     * @exception NullVectorException if the vector cannot be normalized.
     */
    public Vector2D normalize() throws NullVectorException {
        if (Geo2D.isNull(this)) {
            throw new NullVectorException("Can't normalize a null vector");
        }
        double len = Geo2D.length(this);
        this.x /= len;
        this.y /= len;
        return this;
    }

    // =========================================================================
    // Vector operations =======================================================
    // =========================================================================

    /**
     * Calculates the scalar product between this and the vec
     * <code>Vector2D</code>.
     * @param vec the vector
     */
    public double getScalarProduct(Vector2D vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    /**
     * Calculates the angle from this <code>Vector2D</code> to the vec
     * <code>Vector2D</code>. The angle is measured in a counter clockwise
     * direction.<br/>
     * <b>Example:</b><br/><dl>
     *   <dd>X_UNIT.angleCCW(Y_UNIT) = 0.5 * Math.PI</dd>
     *   <dd>Y_UNIT.angleCCW(X_UNIT) = 1.5 * Math.PI</dd>
     *   <dd>X_UNIT.angleCCW(X_UNIT) = 0</dd>
     * </dl>
     * @param vec the vector
     * @return the angle in the range 0 <= angle < 2 * Math.PI
     * @exception NullVectorException if this or vec is null.
     */
    public double getAngleCCW(Vector2D vec) throws NullVectorException {
        if (Geo2D.isNull(this) || Geo2D.isNull(vec)) {
            throw new NullVectorException(
                    "Can't calculate angle between null vectors");
        }

        double ang1 = Math.atan2(this.getY(), this.getX());
        double ang2 = Math.atan2(vec.getY(), vec.getX());
        double ang = ang2 - ang1 + Math.PI + Math.PI;
        return ang % (Math.PI + Math.PI);
    }

    /**
     * Calculates the smallest angle between this <code>Vector2D</code> and the
     * vec <code>Vector2D</code>.<br/>
     * <b>Example:</b><br/><dl>
     *   <dd>X_UNIT.smallestAngle(Y_UNIT) = 0.5 * Math.PI</dd>
     *   <dd>Y_UNIT.smallestAngle(X_UNIT) = 0.5 * Math.PI</dd>
     *   <dd>X_UNIT.smallestAngle(X_UNIT) = 0</dd>
     * </dl>
     * @param vec the vector
     * @return the angle in the range 0 <= angle < Math.PI
     * @exception NullVectorException if this or vec is null.
     */
    public double getAngleSmallest(Vector2D vec) throws NullVectorException {
        if (Geo2D.isNull(this) || Geo2D.isNull(vec)) {
            throw new NullVectorException(
                    "Can't calculate angle between null vectors");
        }

        return Math.acos(this.getScalarProduct(vec) /
                (Geo2D.length(this) * Geo2D.length(vec)));
    }

    // =========================================================================
    // Object methods ==========================================================
    // =========================================================================

    public String toString() {
        return "Vector2D[" + this.x + ", " + this.y + "]";
    }
}