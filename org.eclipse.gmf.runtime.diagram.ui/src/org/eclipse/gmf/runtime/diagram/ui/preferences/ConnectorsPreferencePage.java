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

package org.eclipse.gmf.runtime.diagram.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.gmf.runtime.common.ui.preferences.ComboFieldEditor;
import org.eclipse.gmf.runtime.diagram.ui.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramResourceManager;
import org.eclipse.gmf.runtime.notation.Routing;

/**
 * Connectors preferences page for diagram connector preferences.
 * 
 * @author cmahoney
 *  * @deprecated Renamed to {@link org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage}
 */
public class ConnectorsPreferencePage
	extends AbstractPreferencePage {

	private String LINE_LABEL = DiagramResourceManager
		.getI18NString("ConnectionsPreferencePage.lineStyle.label"); //$NON-NLS-1$

	private ComboFieldEditor lineStyleFieldEditor = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#addFields(org.eclipse.swt.widgets.Composite)
	 */
	protected void addFields(Composite parent) {
		Composite main = createPageLayout(parent);
		addFieldEditors(main);
	}

	/**
	 * Creates a new composite for the entire page and sets up the layout.
	 * 
	 * @param parent
	 *            the parent <code>Composite</code> that the field editors
	 *            will be added to
	 * @return the new <code>Composite</code>
	 */
	private Composite createPageLayout(Composite parent) {
		Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(new GridLayout(2, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		main.setLayoutData(gridData);
		return main;
	}

	/**
	 * Adds the font and color fields to the <code>Composite</code> given.
	 * 
	 * @param composite
	 */
	protected void addFieldEditors(Composite composite) {
		lineStyleFieldEditor = new ComboFieldEditor(
			IPreferenceConstants.PREF_LINE_STYLE, LINE_LABEL, composite,
			ComboFieldEditor.INT_TYPE, true, 0, 0, true);
		lineStyleFieldEditor.autoStorage = true;
		addField(lineStyleFieldEditor);
		Combo lineStyleCombo = lineStyleFieldEditor.getComboControl();
		lineStyleCombo
			.add(DiagramResourceManager
				.getI18NString("ConnectionsPreferencePage.ConnectionView.Manual.text")); //$NON-NLS-1$	
		lineStyleCombo
			.add(DiagramResourceManager
				.getI18NString("ConnectionsPreferencePage.ConnectionView.Rectilinear.text")); //$NON-NLS-1$	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#initHelp()
	 */
	protected void initHelp() {
		// do nothing, help not ready yet
	}

	/**
	 * Initializes the default preference values for this preference store.
	 * 
	 * @param preferenceStore
	 *            the preference store
	 */
	public static void initDefaults(IPreferenceStore preferenceStore) {
		preferenceStore.setDefault(IPreferenceConstants.PREF_LINE_STYLE,
			Routing.MANUAL);
	}
}
