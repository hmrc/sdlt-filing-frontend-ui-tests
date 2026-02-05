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

package uk.gov.hmrc.ui.pages.VendorAgent

import uk.gov.hmrc.ui.pages.BasePage

object AgentReferenceNumberPage extends BasePage {

  override def pageUrl: String = "stamp-duty-land-tax-filing/about-the-vendor/enter-agent-reference-number"

  override def pageTitle: String =
    "What is the agent’s reference for this return? - About the vendor’s agent - Stamp Taxes Online - GOV.UK"

  def agentReference: String = "agentReference"

  def agentReferenceNumber: String = "Agent_001"

  def agentReferenceNumber2: String = "Agent_002"

}
