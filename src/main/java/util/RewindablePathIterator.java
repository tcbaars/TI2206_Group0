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
 * this program; if not, look at "http://www.gnu.org/copyleft/gpl.html" or write
 * to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA
 *
 * Contact address: berthold.firmenich@cademia.org
 * Homepage       : http://www.cademia.org/
 */

package util;

import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * A <code>RewindablePathIterator</code> extends the <code>PathIterator</code>
 * by the possibility of multiple iterations.
 * <p>
 *
 * @author Berthold Firmenich
 * @version $Revision: 1.2 $, $Author: cvs-richte10 $
 * @version $Date: 2006/12/18 16:19:21 $
 */
public class RewindablePathIterator implements PathIterator {

    private static class Segment {
        private int mSeg;
        private double[] mCrds;
        private Segment(int seg, double[] crds) {
            mSeg = seg;
            mCrds = crds;
        }
    }
    private List mSegments = new ArrayList(); // List of _Seg objects
    private int mCurrentIndex = 0;
    private int mWindingRule;

    // Constructor
    public RewindablePathIterator(PathIterator pit) {

        // Store the winding rule
        mWindingRule = pit.getWindingRule();

        // Store the sequence of segments
        double[] crds = new double[6];
        for (; ! pit.isDone(); pit.next()) {
            int type = pit.currentSegment(crds);
            switch (type) {
                case PathIterator.SEG_MOVETO :
                case PathIterator.SEG_LINETO : {
                    double[] _crds = new double[2];
                    _crds[0] = crds[0];
                    _crds[1] = crds[1];
                    mSegments.add(new Segment(type, _crds));
                }
                break;
                case PathIterator.SEG_QUADTO : {
                    double[] _crds = new double[4];
                    System.arraycopy(crds, 0, _crds, 0, 4);
                    mSegments.add(new Segment(type, _crds));
                }
                break;
                case PathIterator.SEG_CUBICTO : {
                    double[] _crds = new double[6];
                    System.arraycopy(crds, 0, _crds, 0, 6);
                    mSegments.add(new Segment(type, _crds));
                }
                break;
                case PathIterator.SEG_CLOSE :
                    mSegments.add(new Segment(type, new double[0]));
                    break;
                default :
                    throw new Error("Unexpected segment type");
            }
        }
    }

    // Rewind
    public void rewind() {
        mCurrentIndex = 0;
    }

    // PathIterator methods
    public int currentSegment(double[] coords) {
        Segment _seg = (Segment) mSegments.get(mCurrentIndex);
        System.arraycopy(_seg.mCrds, 0, coords, 0, _seg.mCrds.length);
        return _seg.mSeg;
    }
    public int currentSegment(float[] coords) {
        Segment _seg = (Segment) mSegments.get(mCurrentIndex);
        for (int i = 0; i < _seg.mCrds.length; i++)
            coords[i] = (float)_seg.mCrds[i];
        return _seg.mSeg;
    }
    public int getWindingRule() {
        return mWindingRule;
    }
    public boolean isDone() {
        return mCurrentIndex >= mSegments.size();
    }
    public void next() {
        if (! isDone())
            mCurrentIndex++;
    }
}