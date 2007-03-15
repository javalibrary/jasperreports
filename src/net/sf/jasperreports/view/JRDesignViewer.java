/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.InputStream;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JRValidationException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRGraphEnvInitializer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class JRDesignViewer extends javax.swing.JPanel
{


	/**
	 *
	 */
	private static final int TYPE_FILE_NAME = 1;
	private static final int TYPE_INPUT_STREAM = 2;
	private static final int TYPE_JASPER_DESIGN = 3;

	private static final int zooms[] = {50, 75, 100, 125, 150, 175, 200, 250};

	private int type = TYPE_FILE_NAME;
	private boolean isXML = false;
	private String reportFileName = null;
	protected JRDesignRenderer reportRenderer;
	private float zoom = 1f;
	
	/**
	 * the screen resolution.
	 */
	private int screenResolution = JRViewer.REPORT_RESOLUTION;
	
	/**
	 * the zoom ration adjusted to the screen resolution.
	 */
	private float realZoom = 0f;

	private int downX = 0;
	private int downY = 0;

	
	/** Creates new form JRDesignViewer */
	public JRDesignViewer(String fileName, boolean isXML) throws JRException
	{
		JRGraphEnvInitializer.initializeGraphEnv();

		setScreenDetails();

		initComponents();

		this.loadReport(fileName, isXML);
		this.cmbZoom.setSelectedIndex(2);//100%
	}

	
	/** Creates new form JRDesignViewer */
	public JRDesignViewer(InputStream is, boolean isXML) throws JRException
	{
		JRGraphEnvInitializer.initializeGraphEnv();

		setScreenDetails();
		
		initComponents();

		this.loadReport(is, isXML);
		this.cmbZoom.setSelectedIndex(2);//100%
	}

	
	/** Creates new form JRDesignViewer */
	public JRDesignViewer(JRReport report) throws JRException
	{
		JRGraphEnvInitializer.initializeGraphEnv();

		setScreenDetails();

		initComponents();

		this.loadReport(report);
		this.cmbZoom.setSelectedIndex(2);//100%
	}

	
	private void setScreenDetails()
	{
		screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
		setZoom(1f);
	}

	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {//GEN-BEGIN:initComponents
		java.awt.GridBagConstraints gridBagConstraints;

		tlbToolBar = new javax.swing.JPanel();
		btnReload = new javax.swing.JButton();
		pnlSep01 = new javax.swing.JPanel();
		pnlSep02 = new javax.swing.JPanel();
		btnZoomIn = new javax.swing.JButton();
		btnZoomOut = new javax.swing.JButton();
		cmbZoom = new javax.swing.JComboBox();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for(int i = 0; i < zooms.length; i++)
		{
			model.addElement("" + zooms[i] + "%");
		}
		cmbZoom.setModel(model);

		pnlMain = new javax.swing.JPanel();
		scrollPane = new javax.swing.JScrollPane();
		scrollPane.getHorizontalScrollBar().setUnitIncrement(5);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);

		pnlInScroll = new javax.swing.JPanel();
		pnlPage = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		lblPage = new javax.swing.JLabel();

		setLayout(new java.awt.BorderLayout());

		tlbToolBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 2));

		btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/reload.GIF")));
		btnReload.setText("Reload");
		btnReload.setToolTipText("Reload Document");
		btnReload.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnReload.setMaximumSize(new java.awt.Dimension(80, 23));
		btnReload.setMinimumSize(new java.awt.Dimension(80, 23));
		btnReload.setPreferredSize(new java.awt.Dimension(80, 23));
		btnReload.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReloadActionPerformed();
			}
		});

		tlbToolBar.add(btnReload);

		pnlSep01.setMaximumSize(new java.awt.Dimension(10, 10));
		tlbToolBar.add(pnlSep01);

		pnlSep02.setMaximumSize(new java.awt.Dimension(10, 10));
		tlbToolBar.add(pnlSep02);

		btnZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/zoomin.GIF")));
		btnZoomIn.setToolTipText("Zoom In");
		btnZoomIn.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnZoomIn.setMaximumSize(new java.awt.Dimension(23, 23));
		btnZoomIn.setMinimumSize(new java.awt.Dimension(23, 23));
		btnZoomIn.setPreferredSize(new java.awt.Dimension(23, 23));
		btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnZoomInActionPerformed();
			}
		});

		tlbToolBar.add(btnZoomIn);

		btnZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/zoomout.GIF")));
		btnZoomOut.setToolTipText("Zoom Out");
		btnZoomOut.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnZoomOut.setMaximumSize(new java.awt.Dimension(23, 23));
		btnZoomOut.setMinimumSize(new java.awt.Dimension(23, 23));
		btnZoomOut.setPreferredSize(new java.awt.Dimension(23, 23));
		btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnZoomOutActionPerformed();
			}
		});

		tlbToolBar.add(btnZoomOut);

		cmbZoom.setToolTipText("Zoom Ratio");
		cmbZoom.setMaximumSize(new java.awt.Dimension(80, 23));
		cmbZoom.setMinimumSize(new java.awt.Dimension(80, 23));
		cmbZoom.setPreferredSize(new java.awt.Dimension(80, 23));
		cmbZoom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbZoomActionPerformed();
			}
		});

		tlbToolBar.add(cmbZoom);

		add(tlbToolBar, java.awt.BorderLayout.NORTH);

		pnlMain.setLayout(new java.awt.BorderLayout());

		pnlInScroll.setLayout(new java.awt.GridBagLayout());

		pnlPage.setLayout(new java.awt.BorderLayout());

		pnlPage.setMinimumSize(new java.awt.Dimension(100, 100));
		pnlPage.setPreferredSize(new java.awt.Dimension(100, 100));
		jPanel4.setLayout(new java.awt.GridBagLayout());

		jPanel4.setMinimumSize(new java.awt.Dimension(100, 120));
		jPanel4.setPreferredSize(new java.awt.Dimension(100, 120));
		jPanel5.setBackground(java.awt.Color.gray);
		jPanel5.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel5.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		jPanel4.add(jPanel5, gridBagConstraints);

		jPanel6.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel6.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		jPanel4.add(jPanel6, gridBagConstraints);

		jPanel7.setBackground(java.awt.Color.gray);
		jPanel7.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel7.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel4.add(jPanel7, gridBagConstraints);

		jPanel8.setBackground(java.awt.Color.gray);
		jPanel8.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel8.setPreferredSize(new java.awt.Dimension(5, 5));
		jLabel1.setText("jLabel1");
		jPanel8.add(jLabel1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		jPanel4.add(jPanel8, gridBagConstraints);

		jPanel9.setMinimumSize(new java.awt.Dimension(5, 5));
		jPanel9.setPreferredSize(new java.awt.Dimension(5, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel4.add(jPanel9, gridBagConstraints);

		lblPage.setBackground(java.awt.Color.white);
		lblPage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
		lblPage.setOpaque(true);
		lblPage.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblPageMousePressed(evt);
			}
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblPageMouseReleased();
			}
		});

		lblPage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				lblPageMouseDragged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(lblPage, gridBagConstraints);

		pnlPage.add(jPanel4, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		pnlInScroll.add(pnlPage, gridBagConstraints);

		scrollPane.setViewportView(pnlInScroll);

		pnlMain.add(scrollPane, java.awt.BorderLayout.CENTER);

		add(pnlMain, java.awt.BorderLayout.CENTER);

	}//GEN-END:initComponents

	void btnReloadActionPerformed()//GEN-FIRST:event_btnReloadActionPerformed
	{//GEN-HEADEREND:event_btnReloadActionPerformed
		// Add your handling code here:
		if (this.type == TYPE_FILE_NAME)
		{
			try
			{
				this.loadReport(this.reportFileName, this.isXML);
				//this.cmbZoom.setSelectedIndex(2);//100%
				this.refreshDesign();
			}
			catch (JRException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error loading report design. See console for details.");
			}
		}
	}//GEN-LAST:event_btnReloadActionPerformed

	void btnZoomInActionPerformed()//GEN-FIRST:event_btnZoomInActionPerformed
	{//GEN-HEADEREND:event_btnZoomInActionPerformed
		// Add your handling code here:
		int index = this.cmbZoom.getSelectedIndex();
		if (index < this.cmbZoom.getModel().getSize() - 1)
		{
			this.cmbZoom.setSelectedIndex(index + 1);
		}
	}//GEN-LAST:event_btnZoomInActionPerformed

	void btnZoomOutActionPerformed()//GEN-FIRST:event_btnZoomOutActionPerformed
	{//GEN-HEADEREND:event_btnZoomOutActionPerformed
		// Add your handling code here:
		int index = this.cmbZoom.getSelectedIndex();
		if (index > 0)
		{
			this.cmbZoom.setSelectedIndex(index - 1);
		}
	}//GEN-LAST:event_btnZoomOutActionPerformed

	void lblPageMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblPageMousePressed
	{//GEN-HEADEREND:event_lblPageMousePressed
		// Add your handling code here:
		this.lblPage.setCursor(new Cursor(Cursor.MOVE_CURSOR));

		this.downX = evt.getX();
		this.downY = evt.getY();
	}//GEN-LAST:event_lblPageMousePressed

	void lblPageMouseReleased()//GEN-FIRST:event_lblPageMouseReleased
	{//GEN-HEADEREND:event_lblPageMouseReleased
		// Add your handling code here:
		this.lblPage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}//GEN-LAST:event_lblPageMouseReleased

	void lblPageMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblPageMouseDragged
	{//GEN-HEADEREND:event_lblPageMouseDragged
		// Add your handling code here:
		Container container = pnlInScroll.getParent();
		if (container instanceof JViewport)
		{
			JViewport viewport = (JViewport) container;
			Point point = viewport.getViewPosition();
			int newX = point.x - (evt.getX() - downX);
			int newY = point.y - (evt.getY() - downY);
			
			int maxX = pnlInScroll.getWidth() - viewport.getWidth();
			int maxY = pnlInScroll.getHeight() - viewport.getHeight();

			if (newX < 0)
			{
				newX = 0;
			}
			if (newX > maxX)
			{
				newX = maxX;
			}
			if (newY < 0)
			{
				newY = 0;
			}
			if (newY > maxY)
			{
				newY = maxY;
			}
			
			viewport.setViewPosition(new Point(newX, newY));
		}
	}//GEN-LAST:event_lblPageMouseDragged

	void cmbZoomActionPerformed()//GEN-FIRST:event_cmbZoomActionPerformed
	{//GEN-HEADEREND:event_cmbZoomActionPerformed
		// Add your handling code here:
		int index = this.cmbZoom.getSelectedIndex();
		setZoom(zooms[index] / 100f);
		this.btnZoomIn.setEnabled( (index < this.cmbZoom.getModel().getSize() - 1) );
		this.btnZoomOut.setEnabled( (index > 0) );
		this.refreshDesign();
	}//GEN-LAST:event_cmbZoomActionPerformed


	private void setZoom(float zoom)
	{
		this.zoom = zoom;
		this.realZoom = this.zoom * screenResolution / JRViewer.REPORT_RESOLUTION;
	}


	/**
	*/
	private void verifyDesign(JasperDesign jasperDesign) throws JRException
	{
		/*   */
		Collection brokenRules = JasperCompileManager.verifyDesign(jasperDesign);
		if (brokenRules != null && brokenRules.size() > 0)
		{
			throw new JRValidationException(brokenRules);
		}
	}


	/**
	*/
	private void loadReport(String fileName, boolean isXmlReport) throws JRException
	{
		if (isXmlReport)
		{
			JasperDesign jasperDesign = JRXmlLoader.load(fileName);
			setReport(jasperDesign);
		}
		else
		{
			setReport((JRReport) JRLoader.loadObject(fileName));
		}
		this.type = TYPE_FILE_NAME;
		this.isXML = isXmlReport;
		this.reportFileName = fileName;
		this.btnReload.setEnabled(true);
	}


	/**
	*/
	private void loadReport(InputStream is, boolean isXmlReport) throws JRException
	{
		if (isXmlReport)
		{
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			setReport(jasperDesign);
		}
		else
		{
			setReport((JRReport) JRLoader.loadObject(is));
		}
		this.type = TYPE_INPUT_STREAM;
		this.isXML = isXmlReport;
		this.btnReload.setEnabled(false);
	}


	/**
	*/
	private void loadReport(JRReport rep) throws JRException
	{
		setReport(rep);
		this.type = TYPE_JASPER_DESIGN;
		this.isXML = false;
		this.btnReload.setEnabled(false);
	}
	
	private void setReport(JRReport report) throws JRException
	{
		if (report instanceof JasperDesign)
		{
			verifyDesign((JasperDesign) report);
		}
		
		this.reportRenderer = new JRDesignRenderer(report, this);
	}

	
	/**
	*/
	private void refreshDesign()
	{
		Image image = null;
		ImageIcon imageIcon = null;

		JRDesignRenderer.RenderDimesion renderDim = reportRenderer.getDimension(realZoom);
		Dimension dim = new Dimension(
			renderDim.width + 7, //why 7 ? 2 for the balck border and 5 for the shadow panels
			renderDim.height + 7
			);
		this.pnlPage.setMaximumSize(dim);
		this.pnlPage.setMinimumSize(dim);
		this.pnlPage.setPreferredSize(dim);

		try
		{
			image = reportRenderer.renderToImage(realZoom);
			imageIcon = new ImageIcon(image);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		this.lblPage.setIcon(imageIcon);
	}

	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	protected javax.swing.JPanel tlbToolBar;
	private javax.swing.JPanel pnlInScroll;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel pnlPage;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JPanel pnlMain;
	private javax.swing.JPanel pnlSep02;
	private javax.swing.JButton btnReload;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JButton btnZoomOut;
	private javax.swing.JLabel lblPage;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JButton btnZoomIn;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel pnlSep01;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JComboBox cmbZoom;
	private javax.swing.JPanel jPanel9;
	// End of variables declaration//GEN-END:variables

}
