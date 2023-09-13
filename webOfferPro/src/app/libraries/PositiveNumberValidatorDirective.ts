import { Directive } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors, Validator, ValidatorFn } from '@angular/forms';

@Directive({
  selector: '[appPositiveNumber]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: PositiveNumberValidatorDirective,
      multi: true,
    },
  ],
})
export class PositiveNumberValidatorDirective implements Validator {
  validate(control: AbstractControl): ValidationErrors | null {
    const value = control.value;

    if (value === null || value === undefined || value === '') {
      return null; // Let the 'required' validator handle empty values
    }

    if (isNaN(value) || value <= 0) {
      return { appPositiveNumber: true };
    }

    return null;
  }
}