import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.OutlinedIconButtonComponent
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun RichButtonWithIconAndText(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes title: Int,
    @StringRes desc: Int,
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default)
) {
    val boxModifier = if (enabled)
        modifier
            .clickable { onClick() }
            .alpha(1.0f)
    else
        modifier.alpha(0.3f)

    Box(modifier =boxModifier) {
        Row(
            modifier = Modifier.padding(paddingValues = contentPadding),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedIconButtonComponent(
                icon = icon,
                onClick = onClick,
            )
            Column(
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.medium16),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = stringResource(desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small4),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RichButtonWithIconAndTextPreview() {
    MedicineReminderTheme {
        Surface {
            RichButtonWithIconAndText(
                icon = Icons.Outlined.Phone,
                title = R.string.add_phone_number,
                desc = R.string.keep_doctor_handy,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RichButtonWithIconAndTextDisabledPreview() {
    MedicineReminderTheme {
        Surface {
            RichButtonWithIconAndText(
                icon = Icons.Outlined.Phone,
                title = R.string.add_phone_number,
                desc = R.string.keep_doctor_handy,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onClick = {},
                enabled = false,
            )
        }
    }
}