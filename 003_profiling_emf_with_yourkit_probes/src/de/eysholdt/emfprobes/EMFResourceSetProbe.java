package de.eysholdt.emfprobes;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.yourkit.probes.ForeignKeyColumn;
import com.yourkit.probes.JVM;
import com.yourkit.probes.MethodPattern;
import com.yourkit.probes.ObjectRowIndexMap;
import com.yourkit.probes.OnEnterResult;
import com.yourkit.probes.StringColumn;
import com.yourkit.probes.Table;
import com.yourkit.probes.This;

public class EMFResourceSetProbe {
	private final static ResourceSetTable TABLE_RESOURCESET = new ResourceSetTable();

	private static final class ResourceSetTable extends Table {
		private final StringColumn NAME = new StringColumn("ResourceSet");

		public ResourceSetTable() {
			super("EMF ResourceSets", Table.MASK_FOR_POINT_EVENTS);
		}
	}

	private static final ResourceTable TABLE_RESOURCE = new ResourceTable();

	private static final class ResourceTable extends Table {
		private final ForeignKeyColumn RESOURCESET_ID = new ForeignKeyColumn(TABLE_RESOURCESET);
		private final StringColumn TYPE = new StringColumn("type");
		private final StringColumn URI = new StringColumn("uri");

		public ResourceTable() {
			super("EMF Resource.load()", Table.LASTING_EVENTS | Table.RECORD_STACKTRACE_ON_ROW_CREATION);
		}
	}

	private final static ObjectRowIndexMap<ResourceSet> rs2rwo = new ObjectRowIndexMap<ResourceSet>();

	@MethodPattern("org.eclipse.emf.ecore.resource.impl.ResourceSetImpl:<init>()")
	public static class ResourceSetInit {
		public static void onEnter(@This final ResourceSet rs) {
			synchronized (rs) {
				if (!JVM.isLivePhase())
					return;
				getResourceSetRow(rs);
			}
		}
	}

	private static int getResourceSetRow(ResourceSet rs) {
		synchronized (rs) {
			int i = rs2rwo.get(rs);
			if (i == Table.NO_ROW) {
				i = TABLE_RESOURCESET.createRow();
				String simpleClassName = rs.getClass().getSimpleName();
				String hashCode = Integer.toHexString(System.identityHashCode(rs));
				TABLE_RESOURCESET.NAME.setValue(i, simpleClassName + "@" + hashCode);
				rs2rwo.put(rs, i);
			}
			return i;
		}
	}

	@MethodPattern("org.eclipse.emf.ecore.resource.impl.ResourceImpl:load(java.io.InputStream, java.util.Map)")
	public static class ResourceLoad {

		public static int onEnter(@This final Resource resource) {
			if (!JVM.isLivePhase())
				return -1;
			synchronized (resource) {
				int rsRow = getResourceSetRow(resource.getResourceSet());
				int rRow = TABLE_RESOURCE.createRow();
				TABLE_RESOURCE.RESOURCESET_ID.setValue(rRow, rsRow);
				TABLE_RESOURCE.TYPE.setValue(rRow, resource.getClass().getSimpleName());
				TABLE_RESOURCE.URI.setValue(rRow, resource.getURI().toString());
				return rRow;
			}
		}

		public static void onReturn(@OnEnterResult final int readRowIndex) {
			if (readRowIndex > 0)
				TABLE_RESOURCE.closeRow(readRowIndex);
		}

	}
}
