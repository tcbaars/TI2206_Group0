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


/**
 * Exception to be thrown if geometries do not intersect.
 *
 * @author Berthold Firmenich
 * @version $Revision: 1.4 $, $Author: cvs-richte10 $
 * @version $Date: 2006/12/18 16:19:21 $
 */
public class NoIntersectionException extends RuntimeException {

    private static final long serialVersionUID = 0L;

    public NoIntersectionException() {
    }

    public NoIntersectionException(String message) {
        super(message);
    }
}