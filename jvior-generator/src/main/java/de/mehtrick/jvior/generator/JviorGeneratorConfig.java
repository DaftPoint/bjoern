package de.mehtrick.jvior.generator;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class JviorGeneratorConfig {

	private String path;
	private String folder;

	public JviorGeneratorConfig(String[] args) {
		checkForUnknownProperties(args);
		path = findPropertyInArgs("path", args);
		folder = findPropertyInArgs("folder", args);
	}

	private void checkForUnknownProperties(String[] args) {

	}

	private String findPropertyInArgs(String propertyname, String[] args) {
		for (String arg : args) {
			if (arg.startsWith(propertyname + "=")) {
				return StringUtils.substringAfter(arg, "=");
			}
		}
		return null;
	}

	public boolean isFoldersSet() {
		return folder != null;
	}

}
