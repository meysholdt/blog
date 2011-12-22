package blog_001_xtend_and_swt;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public class XtendSWTLib {

	public static Shell newShell(Display disp, Procedure1<Shell> init) {
		Shell result = new Shell(disp);
		init.apply(result);
		return result;
	}

	public static Button newButton(Composite parent, int style,
			Procedure1<Button> btn) {
		Button b = new Button(parent, style);
		btn.apply(b);
		return b;
	}

	public static Button newButton(int style, Composite parent,
			Procedure1<Button> btn) {
		Button b = new Button(parent, style);
		btn.apply(b);
		return b;
	}

	public static GridData newGridData(Procedure1<GridData> init) {
		GridData gd = new GridData();
		init.apply(gd);
		return gd;
	}

	public static Label newLabel(Composite parent, int style,
			Procedure1<Label> init) {
		Label label = new Label(parent, style);
		init.apply(label);
		return label;
	}

	public static Text newText(Composite parent, int style,
			Procedure1<Text> init) {
		Text text = new Text(parent, style);
		init.apply(text);
		return text;
	}

	public static MessageBox newMessageBox(Shell shell, int style,
			Procedure1<MessageBox> init) {
		MessageBox box = new MessageBox(shell);
		init.apply(box);
		return box;
	}

	public static Group newGroup(Composite parent, int style,
			Procedure1<Group> init) {
		Group group = new Group(parent, style);
		init.apply(group);
		return group;
	}

}
