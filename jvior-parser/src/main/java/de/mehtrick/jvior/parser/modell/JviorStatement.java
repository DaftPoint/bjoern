package de.mehtrick.jvior.parser.modell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.mehtrick.jvior.parser.JviorTextParser;
import lombok.Data;
import lombok.ToString;

/**
 * A more complex extraction of the jvior-spec statement A statement is part of
 * a given, when or then This class contains the original statement as
 * {@link #primitiveStatement} Also it contains a camelCased
 * {@link #statementWithoutParameters}. The parameters are extracted in a list
 * found in {@link #parameters}
 * 
 * @author mehtrick
 *
 */
@Data
@ToString
public class JviorStatement {

	private static final String PARAMETERPATTERN = "\"(.*?)\"";
	private String primitiveStatement;
	private String statementWithoutParameters;
	private List<String> parameters = new ArrayList<>();

	public JviorStatement(String statement, String keyword) {
		setPrimitiveStatement(statement);
		setStatementWithoutParameters(keyword + "_" + removeParametersFromStatement(statement));
		parseParameters(statement);
	}

	private void parseParameters(String statement) {
		Pattern MY_PATTERN = Pattern.compile(PARAMETERPATTERN);
		Matcher matcher = MY_PATTERN.matcher(statement);
		while (matcher.find()) {
			parameters.add(matcher.group().replaceAll("\"", ""));
		}
	}

	private String removeParametersFromStatement(String statement) {
		return Arrays.asList(statement.split(PARAMETERPATTERN)).stream().map(JviorTextParser::parseTextToCamelCase)
				.collect(Collectors.joining(JviorTextParser.BLANK_REPLACEMENT));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof JviorStatement) {
			JviorStatement compareableStatement = (JviorStatement) obj;
			return Objects.equals(compareableStatement.getStatementWithoutParameters(),
					this.getStatementWithoutParameters());

		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return statementWithoutParameters.hashCode();
	}

}
