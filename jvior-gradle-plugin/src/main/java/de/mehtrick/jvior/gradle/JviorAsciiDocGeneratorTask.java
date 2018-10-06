package de.mehtrick.jvior.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import de.mehtrick.jvior.asciidoc.JviorAsciiDocApplication;
import de.mehtrick.jvior.base.JviorGeneratorConfig;
import de.mehtrick.jvior.generator.JviorCodeGeneratorApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class JviorAsciiDocGeneratorTask extends DefaultTask {

	@TaskAction
	public void genAsciiDoc() throws Exception {
		JviorGeneratorExtension extension = getProject().getExtensions().getByType(JviorGeneratorExtension.class);
		JviorGeneratorConfig.setPath(extension.getPath());
		JviorGeneratorConfig.setFolder(extension.getFolder());
		JviorGeneratorConfig.setDocdir(extension.getDocdir());
		JviorGeneratorConfig.setExtendedTestclass(extension.getExtendedTestClass());
		JviorAsciiDocApplication.generateJviorDocs();

	}
}
