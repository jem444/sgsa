package ia.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RegionViewer extends JPanel  {
	private static final long serialVersionUID = 1L;
	protected JPanel regionViewTumb, regionViewTips;
	protected JLabel lNumber, lBounds, lArea;

	
	public RegionViewer(int id, String titulo, String sinopse, final BufferedImage tumb) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 90));
		regionViewTumb = new JPanel() {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {	
				g.drawImage(tumb, 0, 0, 120, 80, new ImageObserver(){
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x,	int y, int width, int height) {
						return false;
					}
					
				});
			}
		};
		regionViewTips = new JPanel(new BorderLayout());
		
		regionViewTumb.setBorder(BorderFactory.createEmptyBorder(60, 40, 60, 40));
		regionViewTips.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 200));
		
		regionViewTumb.setSize(new Dimension(200,200));
		
		lNumber = new JLabel();
		lBounds = new JLabel();
		lArea   = new JLabel();
		setRegion(id, titulo, sinopse);
		
		regionViewTips.add(lNumber, BorderLayout.NORTH);
		regionViewTips.add(lBounds, BorderLayout.CENTER);
		regionViewTips.add(lArea, BorderLayout.SOUTH);
		
		this.add(regionViewTumb, BorderLayout.WEST);
		this.add(regionViewTips, BorderLayout.EAST);
		
		this.setVisible(true);
		
	}
	
	public void setRegion(int id, String titulo, String sinopse) {
		
		lNumber.setText(titulo);
		lBounds.setText("Código: " + id);
		lArea.setText("Sinopse: "+sinopse);
		
		this.repaint();
		this.revalidate();
	}
}
