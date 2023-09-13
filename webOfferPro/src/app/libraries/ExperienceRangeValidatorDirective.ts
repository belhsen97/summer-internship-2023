import { Directive, Input } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, Validator, ValidatorFn, ValidationErrors } from '@angular/forms';
@Directive({
  selector: '[appExperienceRangeValidator]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: ExperienceRangeValidatorDirective,
      multi: true,
    },
  ],
})
export class ExperienceRangeValidatorDirective implements Validator {
  @Input('appExperienceRangeValidator') controlNames: string[] = [];

  validate(control: AbstractControl): ValidationErrors | null {
    const [fromControlName, toControlName] = this.controlNames;
    const fromControl = control.root.get(fromControlName)  ;
    const toControl = control.root.get(toControlName);
    //control.root.get(toControlName); put  [appExperienceRangeValidator]="['add-recruitment-experience-from', 'add-recruitment-experience-to']"  in input
   //control.get(toControlName); put  [appExperienceRangeValidator]="['add-recruitment-experience-from', 'add-recruitment-experience-to']"  in form
    if (!fromControl || !toControl) {
      return null; // If the controls are not found, return null.
    }

    if (fromControl.value !== null && toControl.value !== null) {
      if (fromControl.value >= toControl.value) {
        return { experienceRange: true }; // Validation error if "experienceFrom" is greater than or equal to "experienceTo".
      }
    }

    return null; // Validation passes.
  }
}
