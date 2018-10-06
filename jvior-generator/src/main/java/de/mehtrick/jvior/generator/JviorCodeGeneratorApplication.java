package de.mehtrick.jvior.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import de.mehtrick.jvior.base.AbstractJviorGenerator;
import de.mehtrick.jvior.base.JviorGeneratorConfig;
import de.mehtrick.jvior.base.JviorGeneratorException;
import de.mehtrick.jvior.base.JviorMissingPropertyException;
import de.mehtrick.jvior.parser.JviorParser;
import de.mehtrick.jvior.parser.modell.Jvior;

public class JviorCodeGeneratorApplication extends AbstractJviorGenerator {

	public static void main(String[] args) throws JviorMissingPropertyException, FileNotFoundException {
		JviorGeneratorConfig.init(args);
		generateJviorClasses();
	}

	public static void generateJviorClasses() throws JviorMissingPropertyException, FileNotFoundException {
		JviorGeneratorConfig.validate();
		if (StringUtils.isAllBlank(JviorGeneratorConfig.getPckg())) {
			throw new JviorMissingPropertyException(
					"Please configure the package name by setting the \"pckg\" property");
		}
		if (StringUtils.isAllBlank(JviorGeneratorConfig.getGendir())) {
			throw new JviorMissingPropertyException("Please configure the gendir where the classes will be generated");
		}

		if (JviorGeneratorConfig.isFoldersSet()) {
			File[] files = getFilesFromFolder(JviorGeneratorConfig.getFolder());
			Arrays.asList(files).forEach(f -> generateSingleJvior(f.getPath()));
		} else {
			generateSingleJvior(JviorGeneratorConfig.getPath());
		}
	}

	private static void generateSingleJvior(String path) {
		try {
			Jvior jvior = JviorParser.parseSpec(path);
			JviorCodeGenerator.generate(jvior);
		} catch (Throwable e) {
			throw new JviorGeneratorException(path, e);
		}
	}

}
