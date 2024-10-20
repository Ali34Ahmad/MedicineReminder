package com.example.medicinereminder.data.local.utilities

object RoomConstants {
    const val DATABASE_NAME = "reminder_db"
    const val DATABASE_VERSION = 2

    object Medicine {
        const val TABLE_NAME = "medicine"
        const val COMPANY_NAME = "company_name"
        const val DOCTOR_ID = "doctor_id"
        const val MEDICINE_FORM_ID = "medicine_form_id"
        const val CURRENT_AMOUNT = "current_amount"
        const val DATE_ADDED = "date_added"
        const val LAST_MODIFIED_DATE = "last_modified_date"
        const val TOTAL_MEDICATION_DURATION = "total_medication_duration"
        const val START_DATE = "start_date"
        const val IMAGE_FILE_NAME = "image_file_name"
        const val ID = "id"
    }

    object Doctor {
        const val TABLE_NAME = "doctor"
        const val PHONE_NUMBER = "phone_number"
        const val IMAGE_FILE_NAME = "image_file_name"
        const val ID = "id"
    }

    object MedicineForm {
        const val TABLE_NAME = "medicine_form"
        const val IS_ADDED_BY_USER = "is_added_by_user"
        const val ID = "medicine_from_id"
        const val FORM_NAME = "from_name"
    }

    object Interaction {
        const val TABLE_NAME = "interaction"
        const val DESCRIPTION = "description"
        const val MEDICINE_ID = "medicine_id"
        const val ID = "interaction_id"
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
        const val REMINDER_DATE_ADDED = "reminder_date_added"
        const val IS_REFILL_REMINDER = "is_refill_reminder"
        const val MEDICINE_REMINDER_ID = "medicine_reminder_id"
    }
    object Appointment {
        const val APPOINTMENT_ID = "appointment_id"
        const val TABLE_NAME = "appointment"
        const val DATE_TIME = "date_time"
        const val DOCTOR_ID = "doctor_id"
        const val DATE_ADDED = "date_added"
        const val LAST_MODIFIED_DATE = "last_modified_date"
    }

}