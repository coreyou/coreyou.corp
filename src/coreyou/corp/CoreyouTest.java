package coreyou.corp;

import java.util.ResourceBundle;

import org.eclipse.swt.*;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class CoreyouTest {

	private static ResourceBundle resHello = ResourceBundle.getBundle("coreyouCorp");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Display display = new Display();
		
		final Shell shell = new CoreyouTest().open(display);
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) 
				display.sleep();
		}
		display.dispose();
	}

	public Shell open(Display display) {
		final Color red = new Color(display, 0xFF, 0, 0);
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
//		final Label label = createOneLabel(shell);		
//		shell.addControlListener(new ControlAdapter() {
//			public void controlResized(ControlEvent e) {
//				label.setBounds (shell.getClientArea());
//			}
//		});
		shell.addPaintListener(new PaintListener () {
			public void paintControl(PaintEvent event){
				GC gc = event.gc;
				gc.setForeground(red);
				Rectangle rect = shell.getClientArea();
				gc.drawRectangle(rect.x + 10, rect.y + 10, rect.width - 20, rect.height - 20);
				gc.drawString(resHello.getString("coreyou.corp.swtTest"), rect.x + 20, rect.y + 20);
			}
		});
		shell.addDisposeListener (new DisposeListener () {
			public void widgetDisposed (DisposeEvent e) {
				red.dispose();
			}
		});
		//shell.pack();
		shell.open();
		return shell;
	}
	
	public Label createOneLabel(Shell shell) {
		Label label = new Label (shell, SWT.CENTER);
		label.setText(resHello.getString("coreyou.corp.swtTest") + " " + resHello.getString("coreyou.corp.swtTest2"));
		//label.setBounds(shell.getClientArea());
		//label.pack();
		
		return label;
		
	}
}
