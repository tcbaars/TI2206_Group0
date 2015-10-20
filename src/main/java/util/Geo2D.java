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

import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Supplies methods for geometric calculations in 2D coordinate space. To reuse
 * the geometric data types from the package java.awt.geom all methods are
 * static.
 * The supported geometric types are
 *     {@link Vector2D},
 *     {@link Point2D},
 *     {@link Line2D} (both bounded segments and unbounded lines),
 *     {@link Ellipse2D},
 *     {@link Arc2D},
 *     {@link QuadCurve2D} and
 *     {@link CubicCurve2D}.
 * Length and null properties can be calculated. Geometric calculations are
 * intersection points, equality, parallelism, perpendicularity and
 * containment.
 * The accuracy of calculations can be controlled by an epsilon value.
 * @author Berthold Firmenich
 * @version $Revision: 1.10 $, $Author: cvs-firmenic $
 * @version $Date: 2007/06/20 17:18:51 $
 */
public class Geo2D {

    // =========================================================================
    // Accuracy control ========================================================
    // =========================================================================

    private static double mEps = 1.e-6;
    private static double mEpsSqr = mEps * mEps;

    /**
     * Returns the epsilon value. Two points with a distance less than or equal
     * to epsilon are considered coincident.
     * @return the epsilon value
     */
    public static double getEps() {
        return mEps;
    }

    /**
     * Returns the squared epsilon value.
     * @return the squared epsilon value
     */
    public static double getEpsSqr() {
        return mEpsSqr;
    }

    // =========================================================================
    // Length ==================================================================
    // =========================================================================

    /**
     * Calculates the length of a vector.
     * @param vec the vector
     * @return the length
     */
    public static double length(Vector2D vec) {
        return Math.sqrt(vec.getX() * vec.getX() + vec.getY() * vec.getY());
    }

    // =========================================================================
    // Intersection ============================================================
    // =========================================================================

    // intersection(line segment, *) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Calculates the intersection point between two line segments.
     * @param seg the 1st line segment
     * @param seg2 the 2nd line segment
     * @param pntDst the intersection point
     * @return the intersection point
     * @exception NoIntersectionException if the geometries do not intersect
     */
    public static Point2D intersection(Line2D seg, Line2D seg2, Point2D pntDst)
            throws NoIntersectionException {
        return intersection(seg, false, seg2, false, pntDst);
    }

    // Intersection(line[segment], *) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Calculates the intersection point between two line(segment)s.
     * @param any the 1st line(segment)
     * @param unbounded <code>true</code> if <code>any</code> is a line,
     *     <code>false</code> if <code>any</code> is a segment
     * @param any2 the 2nd line(segment)
     * @param unbounded2 <code>true</code> if <code>any2</code> is a line,
     *     <code>false</code> if <code>any2</code> is a segment
     * @param pntDst the intersection point
     * @return the intersection point
     * @exception NoIntersectionException if the geometries do not intersect
     */
    public static Point2D intersection(Line2D any, boolean unbounded,
                                       Line2D any2, boolean unbounded2, Point2D pntDst)
            throws NoIntersectionException {

        // Create point object if necessary
        if (pntDst == null) {
            pntDst = new Point2D.Double();
        }

        // Bounds check
        if (! unbounded && ! unbounded2) {
            _boundsCheck(any, any2);
        }

        // Segments with coincident endpoints?
        if (! unbounded && ! unbounded2) {
            Point2D[] pa = new Point2D[2];
            pa[0] = any.getP1();
            pa[1] = any.getP2();
            Point2D[] pb = new Point2D[2];
            pb[0] = any2.getP1();
            pb[1] = any2.getP2();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    double dx = pb[j].getX() - pa[i].getX();
                    double dy = pb[j].getY() - pa[i].getY();
                    if ((dx * dx + dy * dy) < getEpsSqr()) {
                        Vector2D va = new Vector2D(pa[i], pa[(i+1)%2]);
                        Vector2D vb = new Vector2D(pb[j], pb[(j+1)%2]);
                        try {
                            double ang = va.getAngleSmallest(vb);
                            if (ang > getEps()) {
                                pntDst.setLocation(pa[i]);
                                return pntDst;
                            }
                        }
                        catch (NullVectorException e) {
                            pntDst.setLocation(pa[i]);
                            return pntDst;
                        }
                    }
                }
            }
        }

        // Linear equation
        double vx1 = any.getX2() - any.getX1();
        double vy1 = any.getY2() - any.getY1();
        double b1  = - vx1;
        double c1  =   vx1 * any.getY1() - vy1 * any.getX1();

        double vx2 = any2.getX2() - any2.getX1();
        double vy2 = any2.getY2() - any2.getY1();
        double b2  = - vx2;
        double c2  =   vx2 * any2.getY1() - vy2 * any2.getX1();

        // Solve equation system
        double d   = vy1 * b2 - vy2 * b1;
        if (Math.abs(d) <= mEps) {
            throw new NoIntersectionException("Lines are parallel");
        }
        double dx  = b1 * c2 - b2 * c1;
        double dy  = c1 * vy2 - c2 * vy1;
        pntDst.setLocation(dx/d, dy/d);

        // Do the line segments contain the intersection point?
        if (! unbounded && ! containment(any, pntDst)) {
            throw new NoIntersectionException(
                    "1st line segment doesn't contain intersection point");
        }
        if (! unbounded2 && ! containment(any2, pntDst)) {
            throw new NoIntersectionException(
                    "2nd line segment doesn't contain intersection point");
        }

        // Return the intersection point
        return pntDst;
    }

    /**
     * Calculates the relative position between a line and a cubic curve. If
     * the line is degenerated (coincident start and end point) then -1 is
     * returned if the line is outside the bounding box of the cubic curve.
     * @param lin the line
     * @param cub the cubic curve
     * @return the position of the curve relative to the line
     *         -1: curve is left of line
     *         +1: curve is right of line
     *          0: curve (eventually) intersects the line
     */
    private static int _relativePosition(Line2D lin, CubicCurve2D cub) {
        // Is the line degenerated?
        if (isNull(lin)) {
            Rectangle2D rct = cub.getBounds2D();
            double x = lin.getX1() - 0.5 * mEps;
            double y = lin.getY1() - 0.5 * mEps;
            return rct.intersects(x, y, mEps, mEps) ?
                    0 : -1;
        }

        // Create a normalized vector perpendicular to the line
        final Vector2D vec1Left = new Vector2D(lin.getP1(), lin.getP2());
        vec1Left.left();
        vec1Left.normalize();

        // All four points/ control points must be left or right the line
        final Vector2D vec = new Vector2D();

        // P1
        vec.setLocation(lin.getP1(), cub.getP1());
        int ii = _relativePosition(vec1Left, vec);
        if (ii == 0) {
            return 0;
        }

        // P2
        vec.setLocation(lin.getP1(), cub.getP2());
        int i = _relativePosition(vec1Left, vec);
        if (i == 0 || i + ii == 0) {
            return 0;
        }

        // CtrlP1
        vec.setLocation(lin.getP1(), cub.getCtrlP1());
        i = _relativePosition(vec1Left, vec);
        if (i == 0 || i + ii == 0) {
            return 0;
        }

        // CtrlP2
        vec.setLocation(lin.getP1(), cub.getCtrlP2());
        i = _relativePosition(vec1Left, vec);
        if (i == 0 || i + ii == 0) {
            return 0;
        }

        return ii;
    }
    private static int _relativePosition(Vector2D vec1Left, Vector2D vec1Pnt) {
        double signedDist = vec1Left.getScalarProduct(vec1Pnt);
        if (signedDist < -mEps) {
            return -1;
        } else if (signedDist > +mEps) {
            return +1;
        } else {
            return 0;
        }
    }

    // Intersection(ellipse, *) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Calculates the intersection points between two ellipses.
     * @param ell the 1st ellipse
     * @param ell2 the 2nd ellipse
     * @return the iterator for the intersection points
     * @exception NoIntersectionException if the geometries do not intersect
     */
    public static Boolean intersection(Ellipse2D ell, Ellipse2D ell2) {
        try {
            intersection((ell).getPathIterator(null), (ell2).getPathIterator(null));
            return true;
        } catch (NoIntersectionException e) {
            return false;
        }
    }

    // Intersection(cubic parametric curve segment, *) >>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Calculates the intersection points between two cubic parametric curve
     * segment.
     * @param cub1 the 1st cubic parametric curve segment
     * @param cub2 the 2nd cubic parametric curve segment
     * @return the iterator for the intersection points
     * @exception NoIntersectionException if the geometries do not intersect
     */
    public static Iterator intersection(CubicCurve2D cub1, CubicCurve2D cub2)
            throws NoIntersectionException {
        // Intersect
        final Point2D[] pnts = new Point2D.Double[9];
        final int nPnts = _intersection(cub1, cub2, pnts, 0);

        // Provide results
        if (nPnts == 0) {
            throw new NoIntersectionException();
        }
        return new PointIterator(pnts, nPnts);
    }

    private static class PointIterator implements Iterator {
        private Point2D[] mPnts;
        private int mIndex = 0;
        private PointIterator(Point2D[] pnts, int nPnts) {
            mPnts = new Point2D.Double[nPnts];
            System.arraycopy(pnts, 0, mPnts, 0, nPnts);
            mIndex = 0;
        }
        public boolean hasNext() {
            return mIndex < mPnts.length;
        }
        public Object next() {
            if (mIndex >= mPnts.length) {
                throw new NoSuchElementException();
            }
            return mPnts[mIndex++];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private static int _intersection(CubicCurve2D cub1, CubicCurve2D cub2,
                                     Point2D[] pnts, int nPnts) {

        // Maximum number of intersection points reached?
        if (nPnts >= 9) {
            return nPnts;
        }

        // Intersection possible?
        if (! _collision(cub1, cub2)) {
            return nPnts;
        }

        // Both curves approximately straight?
        boolean isFlat1 = cub1.getFlatnessSq() < mEpsSqr;
        boolean isFlat2 = cub2.getFlatnessSq() < mEpsSqr;
        if (isFlat1 && isFlat2) {
            // Calculate intersection point
            Line2D l1 = new Line2D.Double(cub1.getP1(), cub1.getP2());
            Line2D l2 = new Line2D.Double(cub2.getP1(), cub2.getP2());
            Point2D p = new Point2D.Double();
            try {
                intersection(l1, l2, p);

                // Store point if not already stored?
                for (int i = 0; i < nPnts; i++) {
                    if (equality(pnts[i], p)) {
                        return nPnts;
                    }
                }
                pnts[nPnts++] = p;
            }
            catch (NoIntersectionException e) {
            }
        }

        // Subdivide and intersect again
        else if (isFlat1) {
            // Curve 2 left or right of line 1?
            Line2D l1 = new Line2D.Double(cub1.getP1(), cub1.getP2());
            if (_relativePosition(l1, cub2) != 0) {
                return nPnts;
            }

            // Subdivide curve 2
            CubicCurve2D left2 = new CubicCurve2D.Double();
            CubicCurve2D right2 = new CubicCurve2D.Double();
            cub2.subdivide(left2, right2);

            nPnts = _intersection(cub1, left2, pnts, nPnts);
            nPnts = _intersection(cub1, right2, pnts, nPnts);
        }
        else if (isFlat2) {
            // Curve 1 left or right of line 2?
            Line2D l2 = new Line2D.Double(cub2.getP1(), cub2.getP2());
            if (_relativePosition(l2, cub1) != 0) {
                return nPnts;
            }

            // Subdivide curve 1
            CubicCurve2D left1 = new CubicCurve2D.Double();
            CubicCurve2D right1 = new CubicCurve2D.Double();
            cub1.subdivide(left1, right1);

            nPnts = _intersection(left1, cub2, pnts, nPnts);
            nPnts = _intersection(right1, cub2, pnts, nPnts);
        }
        else {
            // Subdivide both curves
            CubicCurve2D left1 = new CubicCurve2D.Double();
            CubicCurve2D right1 = new CubicCurve2D.Double();
            cub1.subdivide(left1, right1);

            CubicCurve2D left2 = new CubicCurve2D.Double();
            CubicCurve2D right2 = new CubicCurve2D.Double();
            cub2.subdivide(left2, right2);

            nPnts = _intersection(left1, left2, pnts, nPnts);
            nPnts = _intersection(left1, right2, pnts, nPnts);
            nPnts = _intersection(right1, left2, pnts, nPnts);
            nPnts = _intersection(right1, right2, pnts, nPnts);
        }
        return nPnts;
    }

    // Intersection(path, *) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Calculates the intersection points between two Paths.
     * segment.
     * @param pit1 the 1st path iterator
     * @param pit2 the 2nd path iterator
     * @return the iterator for the intersection points
     * @exception NoIntersectionException if the geometries do not intersect
     */
    public static Iterator intersection(PathIterator pit1, PathIterator pit2)
            throws NoIntersectionException {

        // Second PathIterator must be rewindable
        RewindablePathIterator rwpit2 = new RewindablePathIterator(pit2);

        // Create two cubic curves to save space
        CubicCurve2D cub1 = new CubicCurve2D.Double();
        CubicCurve2D cub2 = new CubicCurve2D.Double();

        // Perform intersection algorithm
        final Set pntSet = new HashSet();
        pntSet.clear();
        Shape seg1;
        double[] mvto1 = new double[2];
        double[] last1 = new double[2];
        double[] crds1 = new double[6];
        for (; ! pit1.isDone(); pit1.next()) {
            int typ1 = pit1.currentSegment(crds1);
            switch (typ1) {
                case PathIterator.SEG_MOVETO :
                    mvto1[0] = crds1[0];
                    mvto1[1] = crds1[1];
                    seg1 = null;
                    last1[0] = crds1[0];
                    last1[1] = crds1[1];
                    break;
                case PathIterator.SEG_LINETO :
                    seg1 = new Line2D.Double(last1[0], last1[1], crds1[0],
                            crds1[1]);
                    last1[0] = crds1[0];
                    last1[1] = crds1[1];
                    break;
                case PathIterator.SEG_QUADTO :
                    seg1 = new QuadCurve2D.Double(last1[0], last1[1], crds1[0],
                            crds1[1], crds1[2], crds1[3]);
                    last1[0] = crds1[2];
                    last1[1] = crds1[3];
                    break;
                case PathIterator.SEG_CUBICTO :
                    seg1 = new CubicCurve2D.Double(last1[0], last1[1], crds1[0],
                            crds1[1], crds1[2], crds1[3], crds1[4], crds1[5]);
                    last1[0] = crds1[4];
                    last1[1] = crds1[5];
                    break;
                case PathIterator.SEG_CLOSE :
                    seg1 = new Line2D.Double(last1[0], last1[1], mvto1[0],
                            mvto1[1]);
                    break;
                default :
                    throw new Error("Unexpected segment type");
            }
            if (seg1 == null) {
                continue;
            }

            // Second curve
            Shape seg2;
            double[] mvto2 = new double[2];
            double[] last2 = new double[2];
            double[] crds2 = new double[6];
            for (rwpit2.rewind(); ! rwpit2.isDone(); rwpit2.next()) {
                int typ2 = rwpit2.currentSegment(crds2);
                switch (typ2) {
                    case PathIterator.SEG_MOVETO :
                        mvto2[0] = crds2[0];
                        mvto2[1] = crds2[1];
                        seg2 = null;
                        last2[0] = crds2[0];
                        last2[1] = crds2[1];
                        break;
                    case PathIterator.SEG_LINETO :
                        seg2 = new Line2D.Double(last2[0], last2[1], crds2[0],
                                crds2[1]);
                        last2[0] = crds2[0];
                        last2[1] = crds2[1];
                        break;
                    case PathIterator.SEG_QUADTO :
                        seg2 = new QuadCurve2D.Double(last2[0], last2[1], crds2[0],
                                crds2[1], crds2[2], crds2[3]);
                        last2[0] = crds2[2];
                        last2[1] = crds2[3];
                        break;
                    case PathIterator.SEG_CUBICTO :
                        seg2 = new CubicCurve2D.Double(last2[0], last2[1], crds2[0],
                                crds2[1], crds2[2], crds2[3], crds2[4], crds2[5]);
                        last2[0] = crds2[4];
                        last2[1] = crds2[5];
                        break;
                    case PathIterator.SEG_CLOSE :
                        seg2 = new Line2D.Double(last2[0], last2[1], mvto2[0],
                                mvto2[1]);
                        break;
                    default :
                        throw new Error("Unexpected segment type");
                }
                if (seg2 != null) {
                    try {
                        if (seg1 instanceof Line2D && seg2 instanceof Line2D) {
                            Point2D p = new Point2D.Double();
                            intersection((Line2D)seg1, (Line2D)seg2, p);
                            _addPoint(pntSet, p);
                        }
                        else {
                            Iterator it = intersection(
                                    _toCubicCurve(seg1, cub1),
                                    _toCubicCurve(seg2, cub2));
                            while (it.hasNext()) {
                                Point2D p = (Point2D)it.next();
                                _addPoint(pntSet, p);
                            }
                        }
                    }
                    catch (NoIntersectionException e) {
                    }
                }
            }
        }
        if (pntSet.isEmpty()) {
            throw new NoIntersectionException();
        }
        return pntSet.iterator();
    }

    // Helpers >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private static void _boundsCheck(Shape shp1, Shape shp2)
            throws NoIntersectionException {
        if (! _collision(shp1, shp2)) {
            throw new NoIntersectionException(
                    "Bounds check: Shapes don't intersect");
        }
    }

    private static boolean _collision(Shape shp1, Shape shp2) {
        // Get shape bounds ...
        Rectangle2D bounds1 = shp1.getBounds2D();
        Rectangle2D bounds2 = shp2.getBounds2D();

        // ... and extend by eps
        bounds1.setFrame(bounds1.getX() - mEps,
                bounds1.getY() - mEps,
                bounds1.getWidth() + mEps + mEps,
                bounds1.getHeight() + mEps + mEps);
        bounds2.setFrame(bounds2.getX() - mEps,
                bounds2.getY() - mEps,
                bounds2.getWidth() + mEps + mEps,
                bounds2.getHeight() + mEps + mEps);
        return bounds1.intersects(bounds2);
    }

    private static CubicCurve2D _toCubicCurve(Shape seg, CubicCurve2D cub) {
        if (cub == null) {
            cub = new CubicCurve2D.Double();
        }
        if (seg instanceof Line2D) {
            Line2D src = (Line2D)seg;
            cub.setCurve(src.getX1(), src.getY1(), src.getX1(), src.getY1(),
                    src.getX2(), src.getY2(), src.getX2(), src.getY2());
        }
        else if (seg instanceof Ellipse2D) {
            throw new InternalError("Can't convert Ellipse2D to CubicCurve2D");
        }
        else if (seg instanceof Arc2D) {
            throw new InternalError("Can't convert Arc2D to CubicCurve2D");
        }
        else if (seg instanceof QuadCurve2D) {
            QuadCurve2D src = (QuadCurve2D)seg;
            cub.setCurve(src.getX1(), src.getY1(), src.getCtrlX(),
                    src.getCtrlY(), src.getCtrlX(), src.getCtrlY(), src.getX2(),
                    src.getY2());
        }
        else {
            CubicCurve2D src = (CubicCurve2D)seg;
            cub.setCurve(src.getX1(), src.getY1(), src.getCtrlX1(),
                    src.getCtrlY1(), src.getCtrlX2(), src.getCtrlY2(), src.getX2(),
                    src.getY2());
        }
        return cub;
    }

    private static boolean _addPoint(Set set, Point2D pnt) {
        for (Object aSet : set) {
            Point2D _pnt = (Point2D) aSet;
            if (equality(pnt, _pnt)) {
                return false;
            }
        }
        set.add(pnt);
        return true;
    }

    // =========================================================================
    // Equality ================================================================
    // =========================================================================

    /**
     * Determines whether or not two points are equal. Two instances of
     * <code>Point2D</code> are equal if their distance is less or equal to
     * <code>HtGeo.getEps()</code>.
     * @param pnt1 the 1st point
     * @param pnt2 the 2nd point
     * @return <code>true</code> if points are equal, <code>false</code> if
     *     points are not equal
     */
    public static boolean equality(Point2D pnt1, Point2D pnt2) {
        double dx = pnt2.getX() - pnt1.getX();
        double dy = pnt2.getY() - pnt1.getY();
        return mEpsSqr > dx * dx + dy * dy;
    }

    // =========================================================================
    // Containment =============================================================
    // =========================================================================

    /**
     * Determines whether a line segment contains a point.
     * @param seg the line segment
     * @param pnt the point
     * @return <code>true</code> if the segment contains the point,
     *     <code>false</code> if not
     */
    public static boolean containment(Line2D seg, Point2D pnt) {
        return seg.ptSegDistSq(pnt) <= mEpsSqr;
    }

    // =========================================================================
    // Null property ===========================================================
    // =========================================================================

    /**
     * Determines whether a vector is null. A vector with coordinates (0, 0) is
     * null.
     * @param vec the vector
     * @return <code>true</code> if the vector null, <code>false</code> if not
     */
    public static boolean isNull(Vector2D vec) {
        return mEpsSqr > vec.getX() * vec.getX() + vec.getY() * vec.getY();
    }

    /**
     * Determines whether a line segment is null. A line segment with length 0
     * is null,
     * @param seg the line segment
     * @return <code>true</code> if the line segment is null, <code>false</code>
     * if not
     */
    public static boolean isNull(Line2D seg) {
        double dx = seg.getX2() - seg.getX1();
        double dy = seg.getY2() - seg.getY1();
        return mEpsSqr > dx * dx + dy * dy;
    }
}