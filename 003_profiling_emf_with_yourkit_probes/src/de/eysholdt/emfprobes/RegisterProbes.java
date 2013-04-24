package de.eysholdt.emfprobes;

import org.eclipse.ui.IStartup;

import com.yourkit.probes.Probes;

public class RegisterProbes implements IStartup {

	@Override
	public void earlyStartup() {
		Probes.registerProbes(EMFResourceSetProbe.class);
	}

}
