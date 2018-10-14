package de.mehtrick.jvior.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import de.mehtrick.jvior.base.JviorGeneratorConfig;
import de.mehtrick.jvior.generator.JviorCodeGeneratorApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class JviorGeneratorTask extends DefaultTask {

	@TaskAction
	public void genJviorCode() throws Exception {
		JviorGeneratorExtension extension = getProject().getExtensions().getByType(JviorGeneratorExtension.class);
		JviorGeneratorConfig.setPath(extension.getPath());
		JviorGeneratorConfig.setFolder(extension.getFolder());
		JviorGeneratorConfig.setGendir(extension.getGendir());
		JviorGeneratorConfig.setPckg(extension.getPckg());
		JviorGeneratorConfig.setExtendedTestclass(extension.getExtendedTestClass());
		JviorCodeGeneratorApplication.generateJviorClasses();

	}
}
