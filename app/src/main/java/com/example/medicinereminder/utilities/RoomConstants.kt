package com.example.medicinereminder.utilities

object RoomConstants {
    const val DATABASE_NAME = "reminder_db"
    const val DATABASE_VERSION = 1

    object Medicine {
        const val TABLE_NAME = "medicine"
        const val COMPANY_NAME = "company_name"
        const val DOCTOR_ID = "doctor_id"
        const val PHARMACEUTICAL_FORM_ID = "pharmaceutical_form_id"
        const val CURRENT_AMOUNT = "current_amount"
        const val DATE_ADDED = "date_added"
        const val LAST_MODIFIED = "last_modified"
        const val TOTAL_MEDICATION_DURATION = "total_medication_duration"
        const val START_DATE = "start_date"
        const val ID = "id"
    }

    object Doctor {
        const val TABLE_NAME = "doctor"
        const val PHONE_NUMBER = "phone_number"
        const val ID = "id"
    }

    object PharmaceuticalForm {
        const val TABLE_NAME = "pharmaceutical_form"
        const val ID = "id"
    }

    object Conflict {
        const val TABLE_NAME = "conflict"
        const val DESCRIPTION = "description"
        const val MEDICINE_ID = "medicine_id"
    }

    object AlternativeMedicine {
        const val TABLE_NAME = "alternative_medicine"
        const val ORIGINAL_MEDICINE_ID = "original_medicine_id"
        const val ALTERNATIVE_MEDICINE_ID = "alternative_medicine_id"
        const val REPLACEMENT_DATE = "replacement_date"
    }

    object DayProgram {
        const val TABLE_NAME = "day_program"
        const val MEDICINE_ID = "medicine_id"
        const val ID = "id"
    }

    object CycleProgram {
        const val TABLE_NAME = "cycle_program"
        const val MEDICINE_ID = "medicine_id"
        const val DOSE_DURATION_BY_DAYS = "dose_duration_by_days"
        const val BREAK_DURATION_BY_DAYS = "break_duration_by_days"
        const val PROGRAM_TYPE = "program_type"
        const val ID = "id"
    }

    object Time {
        const val TABLE_NAME = "time"
        const val DAY_PROGRAM_ID = "day_program_id"
        const val CYCLE_PROGRAM_ID = "cycle_program_id"
        const val DOSE_AMOUNT = "dose_amount"
        const val DATE_ADDED = "date_added"
        const val LAST_MODIFIED_DATE = "last_modified_date"
    }

    object MedicineReminder {
        const val TABLE_NAME = "medicine_reminder"
        const val DATE_TIME = "date_time"
        const val MEDICINE_ID = "medicine_id"
        const val DOSE_AMOUNT = "dose_amount"
        const val DATE_ADDED = "date_added"
        const val IS_REFILL_REMINDER = "is_refill_reminder"
    }
    object Appointment {
        const val TABLE_NAME = "appointment"
        const val DATE_TIME = "date_time"
        const val DOCTOR_ID = "doctor_id"
        const val DATE_ADDED = "date_added"
        const val LAST_MODIFIED_DATE = "last_modified_date"

    }

}