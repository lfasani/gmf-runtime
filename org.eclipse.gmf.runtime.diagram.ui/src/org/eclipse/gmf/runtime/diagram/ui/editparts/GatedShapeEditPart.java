/******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionLabelsEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemContainerFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This is a shape which contains gates. The shape responsible for setting the
 * client area such that gates can be placed on the sides of the shape. Also it
 * is responsible for adding the gate figure into the proper list. Created On:
 * Jul 14, 2003
 * 
 * @author tisrar
 * @author jbruck
 * @deprecated Renamed BorderedShapeEditPart
 */
public abstract class GatedShapeEditPart extends ShapeNodeEditPart {
	/**
	 * Create an instance.
	 * @param view editpart's model
	 */
	public GatedShapeEditPart(View view) {
		super(view);
	}	
	
	/**
	 * Returns the editpart's main figure.
	 * @return <code>IFigure</code>
	 */
	protected IFigure getMainFigure() {
		return getGatedPaneFigure().getElementPane();
	}

	/**
	 * gets this editpart's gate figure.
	 * @return <code>IFigure</code>
	 */
	protected final IFigure getGateFigure() {
		return getGatedPaneFigure().getBorderItemContainer();
	}

	/**
	 * Return the editpart's gated pane figure.
	 * @return <code>IFigure</code>
	 */
	protected final BorderedFigure getGatedPaneFigure() {
		return (BorderedFigure)getFigure();
	}
	
	/**
	 * Sets the supplied constraint on the <tt>childFigure</tt>.
	 * @see org.eclipse.gef.GraphicalEditPart#setLayoutConstraint(EditPart,
	 *      IFigure, Object)
	 */
	public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
		getContentPaneFor((IGraphicalEditPart) child).setConstraint(childFigure, constraint);
	}

	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if ( editPart instanceof BorderItemEditPart ) {
			return getGatedPaneFigure().getBorderItemContainer();
		}
		else {
			return getMainFigure();
		}
	}

	/**
	 * Adds the supplied child to the editpart's gate figure if it is 
	 * an instanceof {@link BorderItemEditPart} and its figure is an instanceof {@link BorderItemFigure}.
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		IFigure childFigure = ((GraphicalEditPart)childEditPart).getFigure();
		if ( childEditPart instanceof BorderItemEditPart && childFigure instanceof BorderItemFigure  ) {
			BorderItemFigure gateFigure = (BorderItemFigure) childFigure; 
			BorderItemContainerFigure gatedFigure = (BorderItemContainerFigure) getContentPaneFor((IGraphicalEditPart) childEditPart);
			IFigure boundaryFig = getGateBoundryFigure();
			if( gateFigure.getLocator() != null ) {
				gatedFigure.addBorderItem(gateFigure, gateFigure.getLocator());
			} else {
				gatedFigure.addBorderItem(gateFigure, new BorderItemFigure.BorderItemLocator(gateFigure, boundaryFig));
			}
		}
		else {
			IFigure fig = getContentPaneFor((IGraphicalEditPart) childEditPart);
			fig.add(childFigure, index);
		}
	}

	/**
	 * Return the figure on which the gate elements will be drawn.
	 * @return {@link #getMainFigure()}
	 */
	protected IFigure getGateBoundryFigure() {
		return getMainFigure();
	}
	
	/**
	 * Remove the supplied child editpart's figure from this editpart's figure.
	 */
	protected void removeChildVisual(EditPart child) {
		IFigure childFigure = ((GraphicalEditPart)child).getFigure();
		if ( child instanceof BorderItemEditPart && childFigure instanceof BorderItemFigure ) {
			BorderItemFigure gateFigure = (BorderItemFigure)childFigure;
			BorderItemContainerFigure gatedFigure = (BorderItemContainerFigure) getContentPaneFor((IGraphicalEditPart) child);
			gatedFigure.removeBorderItem(gateFigure);
		}
		else {
			IFigure fig = getContentPaneFor((IGraphicalEditPart) child);
			fig.remove(childFigure);
		}
	}

	/**
	 * Installs the desired EditPolicies for this.
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.CONNECTION_LABELS_ROLE,	new ConnectionLabelsEditPolicy());

	}
	/**
	 * Returns a {@link BorderedFigure}that will <i>wrap </i> this editpart's
	 * main figure.
	 * 
	 * @see #createMainFigure()
	 */
	protected  NodeFigure createNodeFigure() {
		return new BorderedFigure(createMainFigure());
	}

	/**
	 * Creates this editpart's main figure.
	 * @return the created <code>NodeFigure</code>
	 */
	protected abstract NodeFigure createMainFigure();
}