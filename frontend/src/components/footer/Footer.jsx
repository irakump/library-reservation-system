import React from 'react'
import SocialMedia from './SocialMedia';
import Languages from './Languages';
import Logo from './Logo';

const Footer = () => {
  return (
    <div className='bg-navbar p-5 block'>
      <SocialMedia />
      <Languages />
      <Logo />
    </div>
  )
}

export default Footer;
