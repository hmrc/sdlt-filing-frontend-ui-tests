/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By

object ReturnTaskList extends BasePage {

  override def pageUrl: String = "http://localhost:10910/stamp-duty-land-tax-filing/returnTaskList?returnId=123456"

  def pageUrlNoVendor: String = "http://localhost:10910/stamp-duty-land-tax-filing/returnTaskList?returnId=no-vendor"

  override def pageTitle: String = "Task list - Stamp Taxes Online - GOV.UK"

  val saveAndExit               = "a[href = '/stamp-duty-land-tax-filing/task-list/save-and-exit']"
  val deleteReturn              = "a[href = '/stamp-duty-land-tax-filing/task-list/deleting-SDLT-return']"
  def clickSaveAndExit(): Unit  = click(By.cssSelector(saveAndExit))
  def clickDeleteReturn(): Unit = click(By.cssSelector(deleteReturn))

}
