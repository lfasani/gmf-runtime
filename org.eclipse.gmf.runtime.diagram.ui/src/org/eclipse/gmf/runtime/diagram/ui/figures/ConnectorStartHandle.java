/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
/*
 * Created on Apr 28, 2003
 *
 */
package org.eclipse.gmf.runtime.diagram.ui.figures;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.tools.ConnectionEndpointTracker;

import org.eclipse.gmf.runtime.diagram.ui.tools.ConnectorEndpointTracker;

/**
 * @author mmuszyns
 * @deprecated Renamed to {@link org.eclipse.gef.handles.ConnectionStartHandle}
 */
public class ConnectorStartHandle extends ConnectionHandle {

	/**
	 * constructor
	 * @param owner the edit part that will own the handle
	 */
	public ConnectorStartHandle(ConnectionEditPart owner) {
		setOwner(owner);
		setLocator(new ConnectionLocator(getConnection(),ConnectionLocator.SOURCE));
	}

	/**
	 * constructor
	 * @param owner the edit part that will own the handle
	 * @param fixed code>true</code> if the handle cannot be dragged.
	 */
	public ConnectorStartHandle(ConnectionEditPart owner, boolean fixed) {
		super(fixed);
		setOwner(owner);
		setLocator(new ConnectionLocator(getConnection(),ConnectionLocator.SOURCE));
	}

	/**
	 * Creates and returns a new {@link ConnectionEndpointTracker}.
	 */
	protected DragTracker createDragTracker() {
		if (isFixed()) 
			return null;
		ConnectionEndpointTracker tracker;
		tracker = new ConnectorEndpointTracker((ConnectionEditPart)getOwner());
		tracker.setCommandName(RequestConstants.REQ_RECONNECT_SOURCE);
		tracker.setDefaultCursor(getCursor());
		return tracker;
	}
}
