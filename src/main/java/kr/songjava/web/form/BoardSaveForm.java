package kr.songjava.web.form;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import kr.songjava.web.validation.ValidationSteps;
import lombok.Data;

@Data
@GroupSequence({
	BoardSaveForm.class,
	ValidationSteps.Step1.class,
	ValidationSteps.Step2.class,
	ValidationSteps.Step3.class,
	ValidationSteps.Step4.class,
})
public class BoardSaveForm {

	private int boardSeq;

	@NotEmpty(groups = ValidationSteps.Step1.class, message = "{BoardSaveForm.title.NotEmpty}")
	@Length(min = 2, max = 50, groups = ValidationSteps.Step2.class, message = "{BoardSaveForm.title.Length}")
	private String title;
	
	@NotEmpty(groups = ValidationSteps.Step3.class, message = "{BoardSaveForm.contents.NotEmpty}")
	@Length(min = 5, max = 500, groups = ValidationSteps.Step4.class, message = "{BoardSaveForm.contents.Length}")
	private String contents;

}
