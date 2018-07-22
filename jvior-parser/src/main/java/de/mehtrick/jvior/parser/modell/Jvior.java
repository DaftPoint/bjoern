package de.mehtrick.jvior.parser.modell;

import java.util.List;
import java.util.stream.Collectors;

import de.mehtrick.jvior.parser.JviorTextParser;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Jvior {
	private String feature;
	private List<JviorScenario> scenarios;

	public Jvior(JviorYMLModell yamlModell) {
		setFeature(yamlModell.getFeature());
		setScenarios(yamlModell.getScenarios().stream().map(JviorScenario::new).collect(Collectors.toList()));
	}

	public String getFeatureNameFormatted() {
		return JviorTextParser.parseTextToCamelCase(getFeature());
	}

}
