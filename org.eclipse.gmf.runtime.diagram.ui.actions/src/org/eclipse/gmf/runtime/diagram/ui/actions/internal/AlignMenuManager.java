/******************************************************************************
 * Copyright (c) 2002, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.actions.internal;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramActionsResourceManager;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ActionIds;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author melaasar
 * @canBeSeenBy %level1
 *
 * The align menu manager. It contains all align-related actions
 */
public class AlignMenuManager extends ActionMenuManager {

	/**
	 * The align menu action containing the UI for the align menu manager
	 */
	private static class AlignMenuAction extends Action {
		public AlignMenuAction() {
			setText(DiagramActionsResourceManager.getI18NString("AlignActionMenu.AlignDropDownText")); //$NON-NLS-1$
			setToolTipText(DiagramActionsResourceManager.getI18NString("AlignActionMenu.AlignDropDownTooltip")); //$NON-NLS-1$
			ImageDescriptor imageDesc = DiagramActionsResourceManager
				.getInstance().getImageDescriptor(
					DiagramActionsResourceManager.IMAGE_ALIGN);
			setImageDescriptor(imageDesc);
			setHoverImageDescriptor(imageDesc);
		}
	}

	/**
	 * Creates a new instance of the align menu manager
	 */
	public AlignMenuManager() {
		super(ActionIds.MENU_ALIGN, new AlignMenuAction(), true);
	}

}
