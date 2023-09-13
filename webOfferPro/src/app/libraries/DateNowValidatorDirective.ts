import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[appDateNow]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: DateNowValidatorDirective,
      multi: true,
    },
  ],
})
export class DateNowValidatorDirective implements Validator { 

  validate(control: AbstractControl): ValidationErrors | null {
    if (!control.value) {
      return null; // If either the input or dateNow is not provided, skip validation
    }

    const inputDate = new Date(control.value);
    const lowerDate = new Date();

    if (inputDate <= lowerDate) {
      return { dateNow: true }; // Validation failed
    }

    return null; // Validation passed
  }
}
