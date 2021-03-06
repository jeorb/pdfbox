/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSString;

/**
 * Base class for fields which use "Variable Text".
 * These fields construct an appearance stream dynamically at viewing time.
 *
 * @author Ben Litchfield
 */
public abstract class PDVariableText extends PDField
{
    /**
     * Ff flags.
     */
    private static final int FLAG_MULTILINE = 1 << 12;
    private static final int FLAG_PASSWORD = 1 << 13;
    private static final int FLAG_FILE_SELECT = 1 << 20;
    private static final int FLAG_DO_NOT_SPELL_CHECK = 1 << 22;
    private static final int FLAG_DO_NOT_SCROLL = 1 << 23;
    private static final int FLAG_COMB = 1 << 24;
    private static final int FLAG_RICH_TEXT = 1 << 25;


    /**
     * DA    Default appearance.
     */
    private COSString defaultAppearance;

    /**
     * A Q value.
     */
    public static final int QUADDING_LEFT = 0;

    /**
     * A Q value.
     */
    public static final int QUADDING_CENTERED = 1;

    /**
     * A Q value.
     */
    public static final int QUADDING_RIGHT = 2;

    /**
     * @see PDField#PDField(PDAcroForm,COSDictionary)
     *
     * @param theAcroForm The acroform.
     */
    PDVariableText(PDAcroForm theAcroForm)
    {
        super( theAcroForm );
    }

    /**
     * Constructor.
     * 
     * @param theAcroForm The form that this field is part of.
     * @param field the PDF object to represent as a field.
     * @param parentNode the parent node of the node to be created
     */
    protected PDVariableText(PDAcroForm theAcroForm, COSDictionary field, PDFieldTreeNode parentNode)
    {
        super( theAcroForm, field, parentNode);
    }

    /**
     * @return true if the field is multiline
     */
    public boolean isMultiline()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_MULTILINE );
    }

    /**
     * Set the multiline bit.
     *
     * @param multiline The value for the multiline.
     */
    public void setMultiline( boolean multiline )
    {
        getDictionary().setFlag( COSName.FF, FLAG_MULTILINE, multiline );
    }

    /**
     * @return true if the field is a password field.
     */
    public boolean isPassword()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_PASSWORD );
    }

    /**
     * Set the password bit.
     *
     * @param password The value for the password.
     */
    public void setPassword( boolean password )
    {
        getDictionary().setFlag( COSName.FF, FLAG_PASSWORD, password );
    }

    /**
     * @return true if the field is a file select field.
     */
    public boolean isFileSelect()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_FILE_SELECT );
    }

    /**
     * Set the file select bit.
     *
     * @param fileSelect The value for the fileSelect.
     */
    public void setFileSelect( boolean fileSelect )
    {
        getDictionary().setFlag( COSName.FF, FLAG_FILE_SELECT, fileSelect );
    }

    /**
     * @return true if the field is not suppose to spell check.
     */
    public boolean doNotSpellCheck()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_DO_NOT_SPELL_CHECK );
    }

    /**
     * Set the doNotSpellCheck bit.
     *
     * @param doNotSpellCheck The value for the doNotSpellCheck.
     */
    public void setDoNotSpellCheck( boolean doNotSpellCheck )
    {
        getDictionary().setFlag( COSName.FF, FLAG_DO_NOT_SPELL_CHECK, doNotSpellCheck );
    }

    /**
     * @return true if the field is not suppose to scroll.
     */
    public boolean doNotScroll()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_DO_NOT_SCROLL );
    }

    /**
     * Set the doNotScroll bit.
     *
     * @param doNotScroll The value for the doNotScroll.
     */
    public void setDoNotScroll( boolean doNotScroll )
    {
        getDictionary().setFlag( COSName.FF, FLAG_DO_NOT_SCROLL, doNotScroll );
    }

    /**
     * @return true if the field is not suppose to comb the text display.
     */
    public boolean isComb()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_COMB );
    }

    /**
     * Set the comb bit.
     *
     * @param comb The value for the comb.
     */
    public void setComb( boolean comb )
    {
        getDictionary().setFlag( COSName.FF, FLAG_COMB, comb );
    }

    /**
     * @return true if the field is a rich text field.
     */
    public boolean isRichText()
    {
        return getDictionary().getFlag( COSName.FF, FLAG_RICH_TEXT );
    }

    /**
     * Set the richText bit.
     *
     * @param richText The value for the richText.
     */
    public void setRichText( boolean richText )
    {
        getDictionary().setFlag( COSName.FF, FLAG_RICH_TEXT, richText );
    }

    /**
     * Get the default appearance.
     * 
     * This is an inheritable attribute.
     * 
     * The default appearance contains a set of default graphics and text operators
     * to define the field’s text size and color.
     * 
     * @return the DA element of the dictionary object
     */
    public COSString getDefaultAppearance()
    {
        COSBase daValue = getInheritableAttribute(COSName.DA);
        defaultAppearance = (COSString) daValue;
        return defaultAppearance;
    }

    /**
     * Set the default appearance.
     * 
     * This will set the local default appearance for the variable text field only not 
     * affecting a default appearance in the parent hierarchy.
     * 
     * Providing null as the value will remove the local default appearance.
     * 
     * @param daValue a string describing the default appearance
     */
    public void setDefaultAppearance(String daValue)
    {
        if (daValue != null)
        {
            defaultAppearance = new COSString(daValue);
            getDictionary().setItem(COSName.DA, defaultAppearance);
        }
        else
        {
            defaultAppearance = null;
            getDictionary().removeItem(COSName.DA);
        }
    }
    
    /**
     * Get the default style string.
     * 
     * The default style string defines the default style for
     * rich text fields.
     * 
     * @return the DS element of the dictionary object
     */
    public String getDefaultStyleString()
    {
        COSString defaultStyleString = (COSString) getDictionary().getDictionaryObject(COSName.DS);
        return defaultStyleString.getString();
    }

    /**
     * Set the default style string.
     * 
     * Providing null as the value will remove the default style string.
     * 
     * @param defaultStyleString a string describing the default style.
     */
    public void setDefaultStyleString(String defaultStyleString)
    {
        if (defaultStyleString != null)
        {
            getDictionary().setItem(COSName.DS, new COSString(defaultStyleString));
        }
        else
        {
            getDictionary().removeItem(COSName.DS);
        }
    }    

    /**
     * This will get the 'quadding' or justification of the text to be displayed.
     * 
     * This is an inheritable attribute.
     * 
     * 0 - Left(default)<br/>
     * 1 - Centered<br />
     * 2 - Right<br />
     * Please see the QUADDING_CONSTANTS.
     *
     * @return The justification of the text strings.
     */
    public int getQ()
    {
        int retval = 0;

        COSNumber number = (COSNumber)getInheritableAttribute(COSName.Q );
        
        if( number != null )
        {
            retval = number.intValue();
        }
        return retval;
    }

    /**
     * This will set the quadding/justification of the text.  See QUADDING constants.
     *
     * @param q The new text justification.
     */
    public void setQ( int q )
    {
        getDictionary().setInt( COSName.Q, q );
    }
    
    @Override
    public Object getDefaultValue()
    {
        return getInheritableAttribute(COSName.DV);
    }

    /**
     * Set the fields default value.
     *
     * @param value the fields value
     */
    public void setDefaultValue(Object value)
    {
        // Text fields don't support the "DV" entry.
        throw new IllegalArgumentException( "Text fields don't support the \"DV\" entry." );
    }
    
    /**
     * Get the fields rich text value.
     * 
     * @return the rich text value string
     */
    public String getRichTextValue()
    {
        COSBase richTextValue = getDictionary().getDictionaryObject(COSName.RV);
        
        if (richTextValue instanceof COSString)
        {
            return ((COSString) richTextValue).getString();
        }
        // TODO stream instead of string
        return "";
    }
    
    /**
     * Set the fields rich text value.
     * 
     * Setting the rich text value will not generate the appearance
     * for the field.
     * 
     * You can set {@link PDAcroForm#setNeedAppearances(Boolean)} to
     * signal a conforming reader to generate the appearance stream.
     * 
     * Providing null as the value will remove the default style string.
     * 
     * @param richTextValue a rich text string
     */
    public void setRichTextValue(String richTextValue)
    {
        // TODO stream instead of string
        if (richTextValue != null)
        {
            getDictionary().setItem(COSName.RV, new COSString(richTextValue));
        }
        else
        {
            getDictionary().removeItem(COSName.RV);
        }        
    }
}
